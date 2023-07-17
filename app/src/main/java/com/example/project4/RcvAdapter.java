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

import com.example.project4.model.Host;

import java.util.ArrayList;
import java.util.List;

public class RcvAdapter extends RecyclerView.Adapter<RcvViewHolder> implements Filterable {
    private List<Host> listHost;
    private List<Host> listHostFilter;
    private IClickItemView iClickItemView;
    private Context context;
    private RecyclerView.Adapter ImgeAdapter;

    public RcvAdapter(Context context,List<Host> listProduct) {
        this.listHost = listProduct;
        this.context = context;

    }
    public RcvAdapter(Context context,List<Host> listProduct, IClickItemView iClickItemView) {
        this.listHost = listProduct;
        this.iClickItemView = iClickItemView;
        this.context = context;
        this.listHostFilter = listProduct;
    }

    @NonNull
    @Override
    public RcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcv_item, parent, false);
        return new RcvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvViewHolder holder, int position) {
        Host product = listHost.get(position);

        holder.setText(product,context);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rcv_image_house.setLayoutManager(linearLayoutManager);
        ImgeAdapter imgeAdapter = new ImgeAdapter(context);
        imgeAdapter.setData(product.getImageList());
        holder.rcv_image_house.setAdapter(imgeAdapter);
        holder.linearLayout.findViewById(R.id.rcv_image_house).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iClickItemView.onClickView(product);
            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("rcv adapter2| ",product.getName()+" @");
                iClickItemView.onClickView(product);
            }
        });

    }

    @Override
    public int getItemCount() {

        if(listHost == null){
            return 0;
        }else{
            return listHost.size();
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    listHost = listHostFilter;
                }else {
                    List<Host> list = new ArrayList<>();
                    for (Host host: listHostFilter) {
                        if(host.getAddress().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(host);
                        }
                        if(host.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(host);
                        }
                        if((host.getPrice()+"").contains(strSearch.toLowerCase())){
                            list.add(host);
                        }
                        if(host.getPhone().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(host);
                        }
                    }
                    listHost = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listHost;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listHost = (List<Host>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
