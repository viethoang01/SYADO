package com.example.project4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4.model.Host;
import com.example.project4.model.SYADONotification;

import java.util.ArrayList;
import java.util.List;

public class NotiRcvAdaper extends RecyclerView.Adapter<NotiRcvViewHolder> implements Filterable {
    private List<SYADONotification> listNoti;
    private List<SYADONotification> listNotiOld;
    private IClickItemView iClickItemView;
    private Context context;
    public NotiRcvAdaper(Context context,List<SYADONotification> listNoti) {
        this.listNoti = listNoti;
        this.context = context;

    }
    public NotiRcvAdaper(Context context,List<SYADONotification> listNoti, IClickItemView iClickItemView) {
        this.listNoti = listNoti;
        this.iClickItemView = iClickItemView;
        this.context = context;
        this.listNotiOld = listNoti;
    }

    @NonNull
    @Override
    public NotiRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noti_rcv_items, parent, false);
        return new NotiRcvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotiRcvViewHolder holder, int position) {
        SYADONotification notification = listNoti.get(position);
        holder.setText(notification,context);
    }



    @Override
    public int getItemCount() {

        if(listNoti == null){
            return 0;
        }else{
            return listNoti.size();
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    listNoti = listNotiOld;
                }else {
                    List<SYADONotification> list = new ArrayList<>();
                    for (SYADONotification notification: listNotiOld) {
                        if(notification.getFrom().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(notification);
                        }
                        if(notification.getContent().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(notification);
                        }
                        if(notification.getDate().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(notification);
                        }
                    }
                    listNoti = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listNoti;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listNoti = (List<SYADONotification>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
