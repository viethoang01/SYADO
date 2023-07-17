package com.example.project4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project4.model.Image;

import java.util.List;

public class ImgeAdapter extends RecyclerView.Adapter<ImgeAdapter.ImageViewHolder>{
    private List<Image> imageList;
    public void setData(List<Image> list){
        this.imageList = list;
        notifyDataSetChanged();
    }
    private Context context;

    public ImgeAdapter( Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img,parent,false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            Image image = imageList.get(position);
            if(image == null){
                return;
            }

            Log.e("39 line",""+image.getDataImage());
        Glide.with(context)
                .load(image.getDataImage())
                .placeholder(R.drawable.app_logo)
                .error(R.drawable.app_logo)
                .into(holder.img_house);
        holder.tv_title.setText(image.getImgName());

    }

    @Override
    public int getItemCount() {
        if(imageList.isEmpty()){
            return 0;
        }
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_house;
        private TextView tv_title;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            img_house = itemView .findViewById(R.id.img_house);
            tv_title = itemView.findViewById(R.id.tv_title);

        }
    }
}
