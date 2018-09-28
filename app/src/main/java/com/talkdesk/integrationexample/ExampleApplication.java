package com.talkdesk.integrationexample;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import com.talkdesk.sdk.Talkdesk;
import okhttp3.OkHttpClient;

public class ExampleApplication extends Application {
    private Talkdesk talkdesk;

    @Override
    public void onCreate() {
        super.onCreate();

        final OkHttpClient client = new OkHttpClient.Builder().build();
        talkdesk = new Talkdesk.Builder(getApplicationContext()).enableLogging(true)
                .setAuthorizationManager(new AccessTokenAuthorizationManager(client, new Handler(Looper.getMainLooper()), BuildConfig.API_APP_ID))
                .enableLogging(true).build();

        talkdesk.setup();
    }

    public Talkdesk getTalkdesk() {
        return talkdesk;
    }
}
