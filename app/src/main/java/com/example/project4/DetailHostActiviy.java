package com.example.project4;

import static com.example.project4.DateManager.*;
import static com.example.project4.MyApplication.CHANNEL_ID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project4.model.Comment;
import com.example.project4.model.Host;
import com.example.project4.model.SYADONotification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailHostActiviy extends AppCompatActivity {


    private  final String CHANNEL_ID = "CHANNEL_ID";
    private  final  String CHANNEL_NAME = "CHANNEL_NAME";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 10;
    private static final int REQUEST_CALL = 10;
    private TextView name_tro,price_detail,address_detail,rated_tro,room_num,phone,comment;
    private ImageView img_detail,call;
    private Button booking_btn;
    private List<SYADONotification> notificationList;
    private List<Comment> commentList;
    private TimeDateCurrenrt  timeDateCurrenrt;

    private ImageView o_star,t_star,b_star,bb_star,n_star;
    //
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private CommentAdapter commentAdapter;
    private RecyclerView rcv_comment;
    //
    private Host hostcurrent;
    private List<Host> list;
    private void onBindingView(){
        name_tro = findViewById(R.id.name_tro);
        price_detail = findViewById(R.id.price_detail);
        address_detail = findViewById(R.id.address_detail);
        room_num = findViewById(R.id.room_num);
        img_detail = findViewById(R.id.img_detail);
        phone = findViewById(R.id.phone);
        call = findViewById(R.id.call);
        booking_btn = findViewById(R.id.booking_btn);
        notificationList = new ArrayList<>();
        commentList = new ArrayList<>();
        timeDateCurrenrt = new TimeDateCurrenrt();
        comment = findViewById(R.id.comment);
        rated_tro = findViewById(R.id.rated_tro);
        list = new ArrayList<>();
        o_star= findViewById(R.id.o_star);
        t_star= findViewById(R.id.t_star);
        b_star= findViewById(R.id.b_star);
        bb_star= findViewById(R.id.bo_star);
        n_star= findViewById(R.id.n_star);

    }
    private  void onBindingAction(){
        TimeDateCurrenrt t = new TimeDateCurrenrt();
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null) {
            hostcurrent = (Host) bundle.getSerializable("object_host");



//                call.setOnClickListener(this:: onCall);
        }else{
            Intent i = getIntent();
            if (i != null) {
                String data = i.getStringExtra("name");
                getListHost(data);

            }
        }
        int rated = hostcurrent.getRate();
        if(rated < 4){
            rated_tro.setText("khá tốt");
        }
        if(rated > 4){
            rated_tro.setText("Rất Tốt");
        }
        Log.e("118dasd",""+rated);
        switch (rated){
            case 1:
                o_star.setVisibility(View.VISIBLE);
                t_star.setVisibility(View.GONE);
                b_star.setVisibility(View.GONE);
                bb_star.setVisibility(View.GONE);
                n_star.setVisibility(View.GONE);
                break;
            case 2:
                o_star.setVisibility(View.VISIBLE);
                t_star.setVisibility(View.VISIBLE);
                b_star.setVisibility(View.GONE);
                bb_star.setVisibility(View.GONE);
                n_star.setVisibility(View.GONE);
                break;
            case 3:
                o_star.setVisibility(View.VISIBLE);

                t_star.setVisibility(View.VISIBLE);
                b_star.setVisibility(View.VISIBLE);
                bb_star.setVisibility(View.GONE);
                n_star.setVisibility(View.GONE);
                break;
            case 4:
                o_star.setVisibility(View.VISIBLE);

                t_star.setVisibility(View.VISIBLE);
                b_star.setVisibility(View.VISIBLE);
                bb_star.setVisibility(View.VISIBLE);
                n_star.setVisibility(View.GONE);
                break;
            case 5:
                o_star.setVisibility(View.VISIBLE);
                t_star.setVisibility(View.VISIBLE);
                b_star.setVisibility(View.VISIBLE);
                bb_star.setVisibility(View.VISIBLE);
                n_star.setVisibility(View.VISIBLE);
                break;
            default:
                o_star.setVisibility(View.GONE);
                t_star.setVisibility(View.GONE);
                b_star.setVisibility(View.GONE);
                bb_star.setVisibility(View.GONE);
                n_star.setVisibility(View.GONE);
                break;


        }
        comment.setText(hostcurrent.getComment().size()+" nhận xét");
        name_tro.setText(hostcurrent.getName() == null ? "":"Tên trọ: "+hostcurrent.getName());
        price_detail.setText("Giá phòng: "+t.convertVnd(Integer.parseInt(hostcurrent.getPrice())));
        address_detail.setText(hostcurrent.getAddress() == null? "":"Địa chỉ: "+hostcurrent.getAddress() );
        room_num.setText(hostcurrent.getRoom_num() == 0 ? 0+"":"Số phòng còn trống: "+hostcurrent.getRoom_num() );
        phone.setText("Liên hệ ngay: "+hostcurrent.getPhone());

        if(            hostcurrent.getImageList().get(0) != null){
            Glide.with(this)
                    .load(hostcurrent.getImageList().get(0).getDataImage())
                    .placeholder(R.drawable.app_logo)
                    .error(R.drawable.app_logo)
                    .into(img_detail);
        }

        getNotification();    /// load data of object notification
        booking_btn.setOnClickListener(this:: onBooking);

        getComment();

    }

    private void getListHost(String ho){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("house_list");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    try {
                        Host host = postSnapshot.getValue(Host.class);
                        if(host != null){
                            if("Trọ Bảo Ngọc".equalsIgnoreCase(host.getName()) ){
                                hostcurrent = host;
                            }
                        }
                    }catch (RuntimeException e){
                        Log.e("err",""+e);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void getComment(){
        commentList = hostcurrent.getComment();
        if(commentList.isEmpty()){
            Log.e("dong 124","lisst is null");
        }else {
            Log.e("dong 124", commentList.size()+"");
            List<Comment> commentList1 = new ArrayList<>();
            for (Comment c: commentList
            ) {
                if(c != null){
                    commentList1.add(c);
                }
            }
            rcv_comment = findViewById(R.id.rcv_comment);
            commentAdapter = new CommentAdapter(commentList1);
            layoutManager = new GridLayoutManager(DetailHostActiviy.this,1);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(DetailHostActiviy.this,DividerItemDecoration.VERTICAL);
            rcv_comment.addItemDecoration(dividerItemDecoration);

            rcv_comment.setLayoutManager(layoutManager);
//        rcv_noti.setLayoutManager(layoutManager);
            rcv_comment.setAdapter(commentAdapter);

        }

    }
    ///


    private void getCommentt(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("notifications");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    SYADONotification notification = postSnapshot.getValue(SYADONotification.class);
                    Log.e("SYADONotification",""+notification.toString());
                    notificationList.add(notification);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //

    private void getNotification(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("notifications");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    SYADONotification notification = postSnapshot.getValue(SYADONotification.class);
                    Log.e("SYADONotification",""+notification.toString());
                    notificationList.add(notification);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getCommentHouse(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("house_list");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Comment comment = postSnapshot.getValue(Comment.class);
                    Log.e("commetnt",""+comment.toString());
                    commentList.add(comment);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void onBooking(View view) {
        String userid = FirebaseAuth.getInstance().getUid();
        if(userid == null){
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,SignInActivity.class);
            startActivity(intent);
        }
        else{
            int lastindex = notificationList.get(notificationList.size()-1).getId();
            String message = "Bạn đã đặt phòng của "+name_tro.getText().toString()+", vui lòng chờ sự phản hồi của chủ trọ. Xin cảm ơn";

            SYADONotification syadoNotification = new SYADONotification(lastindex+1,userid,message,timeDateCurrenrt.formattedDate+" "+timeDateCurrenrt.getCurrentTime(),"SYADO");
            onPushDataNotification(syadoNotification,message);
        }
    }

    private void onPushDataNotification(SYADONotification notification,String message) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("notifications");
        String pathObject = String.valueOf(notification.getId());
        myRef.child(pathObject).setValue(notification, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                String message = "Bạn đã đặt phòng của "+name_tro.getText().toString()+", vui lòng chờ sự phản hồi của chủ trọ. Xin cảm ơn";
                pushNotification(message);
            }
        });{

        }
    }


    private void pushNotification(String data) {
        Intent i = new Intent(this,MainActivity.class); // khi ấn vào thông báo thì màn hình sẽ trở lại
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
    private void onCall(View view) {

    }
//    public void makePhoneCall(View view) {
//        int permissionCheck = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION);
//        if (ContextCompat.checkSelfPermission(this, AndroidM Manifest.permission.) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION}, REQUEST_CALL);
//        } else {
//            String phoneNumber = "0123456789";
//            Intent intent = new Intent(Intent.ACTION_CALL);
//            intent.setData(Uri.parse("tel:" + phoneNumber));
//            startActivity(intent);
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CALL) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                makePhoneCall(null);
//            } else {
//                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_host_activiy);
        onBindingView();
        onBindingAction();
    }
}