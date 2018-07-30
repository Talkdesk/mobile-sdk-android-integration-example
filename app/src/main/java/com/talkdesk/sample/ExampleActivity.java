package com.talkdesk.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.talkdesk.sdk.InteractionChannel;
import com.talkdesk.sdk.Talkdesk;
import com.talkdesk.sdk.ui.InteractionChannelFragmentContainer;
import org.jetbrains.annotations.NotNull;

public class ExampleActivity extends AppCompatActivity implements InteractionChannelFragmentContainer {

    private UserCancellationListener listener;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_example_container, new ExampleFragment())
                    .commit();
        }
    }

    @NotNull
    @Override
    public InteractionChannel getInteractionChannel(String intention) {
        return Talkdesk.getInstance().getInteractionChannel(intention);
    }

    @Override
    public void onInteractionChannelClosed() {
        fragmentManager.popBackStack();
    }

    @Override
    public void onBackPressed() {
        if (listener != null) {
            listener.onUserCancelled();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void registerUserCancellationListener(UserCancellationListener userCancellationListener) {
        this.listener = userCancellationListener;
    }

    @Override
    public void unregisterUserCancellationListener() {
        this.listener = null;
    }
}
