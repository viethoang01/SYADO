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
import com.example.project4.model.Comment;
import com.example.project4.model.Host;

public class CommentRcvHolder extends RecyclerView.ViewHolder {
    LinearLayout container_comment;
    RecyclerView rcv_comment;
    private TextView cmt_user_name,cmt_content;

    public CommentRcvHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }
//    private View itemView;

    private void bindingView() {
        container_comment = itemView.findViewById(R.id.container_comment);
        cmt_user_name = itemView.findViewById(R.id.cmt_user_name);
        cmt_content= itemView.findViewById(R.id.cmt_content);
    }

    private void bindingAction() {
//        image.setOnClickListener(this::onTvDataClick);

    }


    public void setText(Comment p ) {
        cmt_content.setText(p.getContent());
        cmt_user_name.setText(p.getUserID());
    }
}
