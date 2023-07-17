package com.example.project4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4.model.Comment;
import com.example.project4.model.Host;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentRcvHolder>{
    private List<Comment> commentList;
    private Context context;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;

    }


    @NonNull
    @Override
    public CommentRcvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentRcvHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull CommentRcvHolder holder, int position) {
        Comment comment = commentList.get(position);

        holder.setText(comment);


//        holder.container_comment.findViewById(R.id.rcv_image_house).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {

        if(commentList == null){
            return 0;
        }else{
            return commentList.size();
        }
    }


}
