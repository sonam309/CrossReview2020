package com.crossreview.Utilites;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDexApplication;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PrefrenceShared extends MultiDexApplication implements LifecycleObserver {

    private static PrefrenceShared prefrenceSharedInstance;
    private PreferenceData preferenceData;
    private FirebaseAnalytics firebaseAnalytics;
    public boolean activityInForground = false;
    private Activity mCurrentActivity = null;
    private String NotificationCount;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    private void initApplication() {
        if (prefrenceSharedInstance == null) {
            ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
            prefrenceSharedInstance = this;
            preferenceData = new PreferenceData(getApplicationContext());
            // Obtain the FirebaseAnalytics instance.
            firebaseAnalytics = FirebaseAnalytics.getInstance(this);
            //firebaseAnalytics.setAnalyticsCollectionEnabled(BuildConfig.ANALYTICS);
            FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onAppForegrounded() {
        activityInForground = !activityInForground;
    }

    @NonNull
    public static PrefrenceShared getInstance() {
        return prefrenceSharedInstance;
    }

    @NonNull
    public PreferenceData getPreferenceData() {
        return preferenceData;
    }

    @NonNull
    public FirebaseAnalytics getFirebaseAnalytics() {
        return firebaseAnalytics;
    }


    private Gson gson = null;

    public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation().create();
        }
        return gson;
    }


    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    public String getNotificationCount() {
        return NotificationCount;
    }

    public void setNotificationCount(String notificationCount) {
        NotificationCount = notificationCount;
    }
}
