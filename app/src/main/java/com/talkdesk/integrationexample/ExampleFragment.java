package com.talkdesk.integrationexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.talkdesk.sdk.InputValue;
import com.talkdesk.sdk.Property;
import com.talkdesk.sdk.Talkdesk;
import com.talkdesk.sdk.ui.InteractionChannelFragment;

import java.util.HashMap;

public class ExampleFragment extends Fragment {

    private Talkdesk talkdesk;
    private FragmentManager fragmentManager;
    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
        talkdesk = ((ExampleApplication) getActivity().getApplicationContext()).getTalkdesk();
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
                talkdesk.getInteractionChannel("simple_callback").open(new HashMap<String, Property>(), false, new HashMap<String, InputValue>());

                fragmentManager.beginTransaction().replace(R.id.activity_example_container, new InteractionChannelFragment.Builder()
                        .setIntention("simple_callback")
                        .build()).addToBackStack("bla").commit();
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
}
