package com.example.myapplication_firebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RestaurantFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RestaurantFragment() {
        // Required empty public constructor
    }

    public static RestaurantFragment newInstance(String param1, String param2) {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        // Set up buttons (assuming you have added them in the XML)
        setupToggle(view, R.id.select_button1);
        setupToggle(view, R.id.select_button2);
        setupToggle(view, R.id.select_button3);
        setupToggle(view, R.id.select_button4);
        setupToggle(view, R.id.select_button5);
        setupToggle(view, R.id.select_button6);

        return view;
    }

    private void setupToggle(View parentView, int buttonId) {
        Button button = parentView.findViewById(buttonId);
        if (button == null) return;

        button.setTag(false); // false = not selected

        button.setOnClickListener(v -> {
            boolean isSelected = (boolean) button.getTag();
            if (isSelected) {
                button.setText("Select");
                button.setTag(false);
            } else {
                button.setText("Unselect");
                button.setTag(true);
            }
        });
    }
}
