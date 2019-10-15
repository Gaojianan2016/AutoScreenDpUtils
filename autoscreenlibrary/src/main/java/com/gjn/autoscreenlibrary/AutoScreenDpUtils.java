package com.gjn.autoscreenlibrary;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author gjn
 * @time 2018/8/22 18:26
 */

public class AutoScreenDpUtils {
    private static final String TAG = "AutoScreenDpUtils";

    private final static String WIDTH_DP = "set_width_dp";

    public static boolean isDebug = false;

    private static Application mApplication;

    private static float w = 0;

    private static float oldDensity = -1;
    private static int oldDensityDpi = -1;
    private static float oldScaledDensity = -1;

    private static float newDensity = -1;
    private static int newDensityDpi = -1;
    private static float newScaledDensity = -1;

    public static void init(@NonNull Application application) {
        init(application, 0);
    }

    public static void init(@NonNull Application application, boolean debug) {
        init(application, 0, debug);
    }

    public static void init(@NonNull Application application, float widthDp) {
        init(application, widthDp, false);
    }

    public static void init(@NonNull Application application, float widthDp, boolean debug) {
        isDebug = debug;
        if (widthDp != 0) {
            w = widthDp;
        } else {
            try {
                ApplicationInfo info = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA);
                w = info.metaData.getInt(WIDTH_DP, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (w == 0) {
            Log.e(TAG, "w is null.");
            return;
        }
        if (mApplication == null) {
            mApplication = application;
        }
        log("-----------------------------------------------");
        log("默认AutoScreenDpUtils.isDebug为false");
        initApplication();
        log("-----------------------------------------------");
    }

    private static void initApplication() {
        final DisplayMetrics metrics = mApplication.getResources().getDisplayMetrics();
        if (oldDensity == -1) {
            oldDensity = metrics.density;
            newDensity = metrics.widthPixels / w;
        }
        if (oldDensityDpi == -1) {
            oldDensityDpi = metrics.densityDpi;
            newDensityDpi = (int) (newDensity * 160);
        }
        if (oldScaledDensity == -1) {
            oldScaledDensity = metrics.scaledDensity;
            newScaledDensity = newDensity * (oldScaledDensity / oldDensity);
        }
        mApplication.registerComponentCallbacks(new ComponentCallbacks() {
            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                if (newConfig != null && newConfig.fontScale > 0) {
                    log("fontScale is change.");
                    newScaledDensity = newDensity * newConfig.fontScale;
                    oldScaledDensity = oldDensity * newConfig.fontScale;
                }
            }

            @Override
            public void onLowMemory() {
            }
        });
        log("oldDensity = " + oldDensity);
        log("oldDensityDpi = " + oldDensityDpi);
        log("oldScaledDensity = " + oldScaledDensity);
        log("newDensity => " + metrics.widthPixels + " / " + w + " = " + newDensity);
        log("newDensityDpi => " + newDensity + " * 160 = " + newDensityDpi);
        log("newScaledDensity => " + newDensity + " * (" + oldScaledDensity + " / " + oldDensity + ") = " + newScaledDensity);
    }

    public static void setCustomDensity(@NonNull final Activity activity) {
        if (mApplication == null) {
            Log.e(TAG, "application is null.");
            return;
        }
        changeDensity(activity);
    }

    private static void changeDensity(@NonNull Activity activity) {
        float density;
        int densityDpi;
        float scaledDensity;
        final DisplayMetrics activityMetrics = activity.getResources().getDisplayMetrics();
        if (activity instanceof IAutoCancel) {
            density = oldDensity;
            densityDpi = oldDensityDpi;
            scaledDensity = oldScaledDensity;
            log("default Density");
        }else if (activity instanceof IAutoChange) {
            density = activityMetrics.widthPixels / ((IAutoChange) activity).newWidth();
            densityDpi = (int) (density * 160);
            scaledDensity = density * (oldScaledDensity / oldDensity);
            log("new Density = " + density);
            log("new dp width = " + ((IAutoChange) activity).newWidth());
            log("new ScaledDensity = " + scaledDensity);
        }else {
            density = newDensity;
            densityDpi = newDensityDpi;
            scaledDensity = newScaledDensity;
            log("new Density = " + density);
            log("new dp width = " + w);
            log("new ScaledDensity = " + scaledDensity);
        }
        activityMetrics.density = density;
        activityMetrics.densityDpi = densityDpi;
        activityMetrics.scaledDensity = scaledDensity;
    }

    private static void log(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

}
