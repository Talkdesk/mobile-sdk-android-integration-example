package com.talkdesk.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.talkdesk.sdk.InteractionChannel;
import com.talkdesk.sdk.Talkdesk;
import com.talkdesk.sdk.ui.InteractionChannelFragment;
import com.talkdesk.sdk.ui.InteractionChannelFragmentContainer;
import org.jetbrains.annotations.NotNull;

public class ExampleActivity extends AppCompatActivity implements InteractionChannelFragmentContainer, ExampleNavigator {

    private UserCancellationListener listener;
    private FragmentManager fragmentManager;
    private String displayedIntention;

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
    public void onInteractionChannelClosed(@NotNull String intention, boolean reopen) {
        if (intention.equals(displayedIntention) && !reopen) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onBackPressed() {
        if (listener != null && displayedIntention != null) {
            listener.onUserCancelled(displayedIntention);
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

    @Override
    public void navigateToInteractionChannelView(String intention) {
        displayedIntention = intention;
        fragmentManager
                .beginTransaction()
                .replace(
                        R.id.activity_example_container,
                        new InteractionChannelFragment.Builder()
                                .setIntention(intention)
                                .build()
                )
                .addToBackStack(intention)
                .commit();
    }
}
