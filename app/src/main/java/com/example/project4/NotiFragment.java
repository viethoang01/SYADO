package com.example.project4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4.model.Host;
import com.example.project4.model.SYADONotification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NotiFragment extends Fragment {

    private RecyclerView rcv_noti;

    private NotiRcvAdaper notiRcvAdaper;
    private RecyclerView.LayoutManager layoutManager;
    private List<SYADONotification> notificationList;
    private View view;
    private NotiFragment notiFragment;
    public NotiFragment() {
        // Required empty public constructor
    }

    private String userid  = FirebaseAuth.getInstance().getUid();

    private void onBindingView(){
        rcv_noti = view.findViewById(R.id.rcv_noti);
        notificationList = new ArrayList<>();
        notiFragment = new NotiFragment();
        notiRcvAdaper = new NotiRcvAdaper(getActivity(),notificationList);
        getNotification();

        layoutManager = new GridLayoutManager(getActivity(),1);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rcv_noti.addItemDecoration(dividerItemDecoration);


    }
    private void onBindingAction(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);            /// revert list data
        linearLayoutManager.setStackFromEnd(true);
        rcv_noti.setLayoutManager(linearLayoutManager);
//        rcv_noti.setLayoutManager(layoutManager);
        rcv_noti.setAdapter(notiRcvAdaper);
    }

    private void getNotification(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("notifications");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    SYADONotification notification = postSnapshot.getValue(SYADONotification.class);
                    if(notification.getUser_id().equals(userid)){
                        Log.e("show",notification.getUser_id()+" | "+userid);
                        notificationList.add(notification);
                    }
                }
                notiRcvAdaper.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(userid != null){
                view =  inflater.inflate(R.layout.fragment_noti, container, false);
                onBindingView();;
                onBindingAction();


        }else{
            view =  inflater.inflate(R.layout.noti_null, container, false);
        }

        return view;

    }
}