package com.example.doan2019;

import android.app.Application;

import com.onesignal.OneSignal;


public class MyApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startOneSignal();
    }
    private void startOneSignal(){
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
