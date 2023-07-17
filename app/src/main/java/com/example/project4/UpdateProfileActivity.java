package com.example.project4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.project4.model.SYADONotification;
import com.example.project4.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText tv_name_profile_update,tv_phone_profile_update,tv_email3_profile_update,tv_address_profile_update,tv_dob_profile_update;
    private Button btn_update_profile;
    private TextView tv_email_update;
    private String userID;
    private void onBindingView(){
        tv_name_profile_update = findViewById(R.id.tv_name_profile_update);
        tv_phone_profile_update = findViewById(R.id.tv_phone_profile_update);
        tv_email3_profile_update = findViewById(R.id.tv_email3_profile_update);
        tv_address_profile_update = findViewById(R.id.tv_address_profile_update);
        tv_dob_profile_update = findViewById(R.id.tv_dob_profile_update);
        btn_update_profile = findViewById(R.id.btn_update_profile);
        tv_email_update = findViewById(R.id.tv_email_update);
        showInformation();
        getUser();

    }
    private void onBindingAction(){
        btn_update_profile.setOnClickListener(this:: onUpdateProfile);
    }

    private  void showInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String email = user.getEmail();
        tv_email_update.setText(email);

    }
    private void onUpdateProfile(View view) {
        String message = "Bạn vừa cập nhật thành công thông tin cá nhân";
         userID = FirebaseAuth.getInstance().getUid();
        String name = tv_name_profile_update.getText().toString();
        String phone = tv_phone_profile_update.getText().toString();
        String email = tv_email3_profile_update.getText().toString();
        String address = tv_address_profile_update.getText().toString();
        String dob = tv_dob_profile_update.getText().toString();

        User user = new User(userID,name,phone,email,address,dob);
        onPushDataUser(user,message);
    }



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
                    if(user !=null){
                        tv_name_profile_update.setText(user.getName());
                        tv_phone_profile_update .setText(user.getPhone());
                        tv_email3_profile_update.setText(user.getEmail());
                        tv_address_profile_update.setText(user.getAddress());
                        tv_dob_profile_update.setText(user.getDOB());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });

    }

    private void onPushDataUser(User user, String message) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        String pathObject = String.valueOf(user.getId());
        myRef.child(pathObject).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                String message = "Bạn đã đặt phòng của "+name_tro.getText().toString()+", vui lòng chờ sự phản hồi của chủ trọ. Xin cảm ơn";
                pushNotification(message);
                Intent i = new Intent(UpdateProfileActivity.this,UpdateProfileActivity.class);
                startActivity(i);
            }
        });{

        }
    }

    private  final String CHANNEL_ID = "CHANNEL_ID";
    private  final  String CHANNEL_NAME = "CHANNEL_NAME";
    private void pushNotification(String data) {
        Intent i = new Intent(this,MoreFragment.class); // khi ấn vào thông báo thì màn hình sẽ trở lại
        i.putExtra("data",data);
        PendingIntent pi =
                PendingIntent.getActivity(this,18, i, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        Notification noti = builder
                .setSmallIcon(R.drawable.armorial_syado_)
                .setContentTitle("SYADO")
                .setContentText(data)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(data))  // mở rộng nội dung thông báo
                .setContentIntent(pi)  // chuyển avtive sau khi click vào thông báo
                .setAutoCancel(true) /// khi ấn vào noti chuyển tới active thì noti sẽ biến mất
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);
        manager.notify(21,noti);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        onBindingView();
        onBindingAction();
    }
}