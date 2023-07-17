package com.example.project4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnbroadingFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnbroadingFragment2 extends Fragment {


    public OnbroadingFragment2() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onbroading2, container, false);
    }
}