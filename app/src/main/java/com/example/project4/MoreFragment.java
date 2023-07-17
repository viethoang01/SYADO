package com.example.project4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.project4.model.Host;
import com.example.project4.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MoreFragment extends Fragment {

    private LinearLayout info_user;
    private Button btn_signup,btn_login;
    private View view ;
    ///
    private TextView tv_email_profile,tv_email3_profile,

            tv_phone_profile,tv_name_profile,tv_address_profile,tv_dob_profile;
    private Button btn_logout;
    private ImageView  img_avatar;
    private String userID;
    private NestedScrollView nested_profile;
    List<User> userList;
    private void onBindingView2(){
        nested_profile = view.findViewById(R.id.nested_profile);
        tv_email_profile = view.findViewById(R.id.tv_email_profile_profile);
        tv_email3_profile = nested_profile.findViewById(R.id.tv_email3_profile);
        tv_phone_profile = nested_profile.findViewById(R.id.tv_phone_profile);
        tv_name_profile = nested_profile.findViewById(R.id.tv_name_profile);
        tv_address_profile = nested_profile.findViewById(R.id.tv_address_profile);
        tv_dob_profile = nested_profile.findViewById(R.id.tv_dob_profile);
        btn_logout = nested_profile.findViewById(R.id.btn_logout);
        img_avatar = view.findViewById(R.id.img_avatar_profile);

        info_user = view.findViewById(R.id.info_user);

        userList = new ArrayList<>();
        showInformation();
       getUser();

     // chay dau

    }

    private void onBindingAction2(){
        btn_logout.setOnClickListener(this:: onLogout);
        img_avatar.setOnClickListener(this:: updateProfile);


        info_user.setOnClickListener(this:: onUpdateProfile);
    }

    private void onUpdateProfile(View view) {
        Intent intent = new Intent(getActivity(),UpdateProfileActivity.class);
        startActivity(intent);
    }

    private  void showInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUri = user.getPhotoUrl();
        if(name == null){
            tv_name_profile.setVisibility(View.GONE);
        }else{
            tv_name_profile.setVisibility(View.VISIBLE);
        }
        tv_name_profile.setText(name);
        tv_email_profile.setText(email);
        Glide.with(this).load(photoUri).error(R.drawable.armorial_syado_);

    }
    private void onLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(),SignInActivity.class);
        startActivity(intent);
    }

    private void onBindingView(){
            btn_signup = view.findViewById(R.id.btn_signup_not_login);
            btn_login = view.findViewById(R.id.btn_login_not_login);
    }

    private void onBindingAction(){
        btn_signup.setOnClickListener(this:: onToSignUp);
        btn_login.setOnClickListener(this:: onToLogin);

    }

    private void updateProfile(View view) {
        Intent intent = new Intent(getActivity(),HomeFragment.class);
        startActivity(intent);
    }

    private void onToLogin(View view) {
        Intent intent  = new Intent(getContext(),SignInActivity.class);
        startActivity(intent);
    }

    private void onToSignUp(View view) {

        Intent intent = new Intent(getActivity(),SingUpActivity.class);
        startActivity(intent);
    }


    public MoreFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            // chua login

            view =  inflater.inflate(R.layout.not_login, container, false);
            onBindingView();
            onBindingAction();

        }else{
            view =  inflater.inflate(R.layout.fragment_more, container, false);
            onBindingView2();
            onBindingAction2();
        }

        return view;
    }

//    private User getUser(){
//
//        List<User> list = new ArrayList<>();
//            User user  = new User();
//            userID =FirebaseAuth.getInstance().getUid();
//        // Get a reference to our posts
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("users/"+userID);
//
//// Attach a listener to read the data at our posts reference
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                User newPost = dataSnapshot.getValue(User.class);
//                System.out.println("Author: " + newPost.author);
//                System.out.println("Title: " + newPost.title);
//                System.out.println("Previous Post ID: " + prevChildKey);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {}
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        });
//    }

//    private void getUser(){
//        User user = new User();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("users");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    User u = postSnapshot.getValue(User.class);
//                    Log.e("getUser", ""+u.toString());
//                    userList.add(u);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(), "on Cancelled", Toast.LENGTH_SHORT).show();
//            }
//        });
//        userID = FirebaseAuth.getInstance().getUid();
//        Log.e("userid","userID: "+userID);
//        for (User u: userList) {
//            Log.e("user 228",u.toString());
//            Log.e("user 228",u.getId().trim().equals(userID.trim())+"");
//
//            if(u.getId().trim().equals(userID.trim())){
//                Log.e("user231","user: "+u.toString());
//                return u;
//            }
//        }
//        return null;
//    }

//    private void getUser(){
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("users");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {   //  chay sau cung
//                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    User user = postSnapshot.getValue(User.class);
//
//                    tv_name_profile.setText(user.getName());
//                    tv_phone_profile.setText(user.getPhone());
//                    tv_email_profile.setText(user.getEmail());
//                    tv_email3_profile.setText(user.getEmail());
//                    tv_address_profile.setText(user.getAddress());
//                    tv_dob_profile.setText(user.getDOB());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        //chay thu 2
//    }

    private void getUser(){
        String userID = FirebaseAuth.getInstance().getUid();
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/"+userID);

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if(user != null){
                    tv_name_profile.setText(user.getName());
                    tv_phone_profile.setText(user.getPhone());
                    tv_email_profile.setText(user.getEmail());
                    tv_email3_profile.setText(user.getEmail());
                    tv_address_profile.setText(user.getAddress());
                    tv_dob_profile.setText(user.getDOB());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}