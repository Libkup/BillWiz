package com.billwiz.admin.billwiz.Activity;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by 李本康 on 2018/9/9
 */

public class BillWizApplication extends Application {

    public static final int VERSION = 120;

    private static Context mContext;

    public static RefWatcher getRefWatcher(Context context) {
        BillWizApplication application = (BillWizApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override public void onCreate() {
        super.onCreate();

        refWatcher = LeakCanary.install(this);
        BillWizApplication.mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return BillWizApplication.mContext;
    }

    public static String getAndroidId() {
        return Settings.Secure.getString(
                getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
