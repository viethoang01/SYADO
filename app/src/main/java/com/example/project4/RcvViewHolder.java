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

public class RcvViewHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    private ImageView image;
    RecyclerView rcv_image_house;
    private TextView textView3,price;
//    private View itemView;

    private void bindingView() {
        rcv_image_house = itemView.findViewById(R.id.rcv_image_house);
        textView3 = itemView.findViewById(R.id.textView3);
        linearLayout = itemView.findViewById(R.id.container);
        price= itemView.findViewById(R.id.price);
        Log.d("rcv Holder| "," @");
    }

    private void bindingAction() {
//        image.setOnClickListener(this::onTvDataClick);

    }



//    private void onItemViewClick(View view) {
//        Toast.makeText(itemView.getContext(), textView3.getText().toString() , Toast.LENGTH_SHORT).show();
//    }

    private void onTvDataClick(View view) {
        Log.d("rcv holder click| ","toat @");
        Toast.makeText(itemView.getContext(), textView3.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public RcvViewHolder(@NonNull View itemView) {
        super(itemView);
//        this.itemView = itemView;
        bindingView();
        bindingAction();
    }

    public void setText(Host p, Context homeFragment ) {
        TimeDateCurrenrt t = new TimeDateCurrenrt();
        Log.e("image",p.getImageList().get(0).getDataImage()+"");
        if(p != null){
//            Glide.with(homeFragment)
//                    .load(p.getImageList().get(0).getDataImage())
//                    .placeholder(R.drawable.app_logo)
//                    .error(R.drawable.app_logo)
//                    .into(image);
            textView3.setText(p.getName());
            if(price != null){
                price.setText(t.convertVnd(Integer.parseInt(p.getPrice()))+"");
            }else{
                Log.e("dong 65", "price null");
            }
        }
    }
}
