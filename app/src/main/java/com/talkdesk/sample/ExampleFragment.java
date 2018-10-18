package com.talkdesk.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.talkdesk.sdk.InputValue;
import com.talkdesk.sdk.Property;
import com.talkdesk.sdk.Talkdesk;

import java.util.HashMap;

public class ExampleFragment extends Fragment {

    public static final String INTENTION_NAME = "simple_callback";
    private Button button;
    private ExampleNavigator navigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ExampleNavigator) {
            navigator = (ExampleNavigator) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.fragment_example_get_support_button);
    }

    @Override
    public void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Talkdesk.getInstance().getInteractionChannel(INTENTION_NAME).open(new HashMap<String, Property>(), false, new HashMap<String, InputValue>());
                navigator.navigateToInteractionChannelView(INTENTION_NAME);
            }
        });
    }

    @Override
    public void onPause() {
        button.setOnClickListener(null);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        button = null;
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        navigator = null;
        super.onDetach();
    }
}
