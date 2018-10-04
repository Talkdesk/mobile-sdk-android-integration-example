package com.talkdesk.integrationexample;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import com.talkdesk.sdk.Talkdesk;
import okhttp3.OkHttpClient;

public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        final OkHttpClient client = new OkHttpClient.Builder().build();
        final AccessTokenAuthorizationManager authorizationManager = new AccessTokenAuthorizationManager(
                client,
                new Handler(Looper.getMainLooper()),
                BuildConfig.API_APP_ID
        );

        Talkdesk.setup(new Talkdesk.Builder(getApplicationContext())
                .enableLogging(true)
                .setAuthorizationManager(authorizationManager)
                .enableLogging(true)
                .build());
    }
}
