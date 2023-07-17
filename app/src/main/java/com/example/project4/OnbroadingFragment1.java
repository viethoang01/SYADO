package com.example.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnbroadingFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnbroadingFragment1 extends Fragment {


    public OnbroadingFragment1() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onbroading1, container, false);
    }
}