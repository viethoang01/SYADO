package com.example.project4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class OnbroadingFragment3 extends Fragment {

  private Button btn_start;
  private View mView;
  private void onBindingView(){

      btn_start = mView.findViewById(R.id.btn_start);
  }
    private void onBindingAction(){
        btn_start.setOnClickListener(this:: onStartApp);
    }

    private void onStartApp(View view) {
    Intent intent = new Intent(getActivity(),SplashActivity.class);
    getActivity().startActivity(intent);

  }

    public OnbroadingFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_onbroading3, container, false);
        onBindingView();
        onBindingAction();

        return mView;
    }
}