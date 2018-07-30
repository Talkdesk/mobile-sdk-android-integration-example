package com.talkdesk.integrationexample;

import android.app.Application;
import com.talkdesk.sdk.Talkdesk;

public class ExampleApplication extends Application {

    private Talkdesk talkdesk;

    @Override
    public void onCreate() {
        super.onCreate();
        talkdesk = new Talkdesk.Builder(getApplicationContext()).enableLogging(true)
                .setDeveloperKey(BuildConfig.DEVELOPER_KEY).build();
        talkdesk.setup();
    }

    public Talkdesk getTalkdesk() {
        return talkdesk;
    }
}
