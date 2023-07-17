package com.example.project4;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project4.model.Host;
import com.example.project4.model.SYADONotification;

public class NotiRcvViewHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    private TextView from_noti,noti_content,noti_time,hello;

    private ImageView img_avatar;

//    private View itemView;

    private void bindingView() {


        linearLayout = itemView.findViewById(R.id.noti_rcv_items);
        from_noti =(TextView) itemView.findViewById(R.id.from_noti);
        noti_content =(TextView) itemView.findViewById(R.id.noti_content);
        noti_time =(TextView) itemView.findViewById(R.id.noti_time);
        img_avatar =(ImageView) itemView.findViewById(R.id.noti_img_avatar);
    }

    private void bindingAction() {

    }


    public NotiRcvViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }

    public void setText(SYADONotification n, Context homeFragment ) {
        Log.e("status st Holder",""+n.toString());
        if(n != null){
            if(!n.getFrom().equals("SYADO")){
                Glide.with(homeFragment)
                        .load(R.drawable.host_icon)
                        .placeholder(R.drawable.host_icon)
                        .error(R.drawable.host_icon)
                        .into(img_avatar);
            }else{
                Glide.with(homeFragment)
                        .load(R.drawable.armorial_syado_)
                        .placeholder(R.drawable.armorial_syado_)
                        .error(R.drawable.armorial_syado_)
                        .into(img_avatar);
            }
            from_noti.setText(n.getFrom());
            noti_content.setText(n.getContent());
            noti_time.setText(n.getDate());
        }else {
            Log.e("Status of set text","notification is null");
        }

    }
}
