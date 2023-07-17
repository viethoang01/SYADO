package com.example.project4;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.project4.model.Host;
import com.example.project4.model.Location;
import com.example.project4.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  {
    private CardView cardView;
    private ImageView button_search;
    private RecyclerView rcv;
    private RcvAdapter adapter;
    private HomeFragment homeFragment;
    private RecyclerView.LayoutManager layoutManager;

    private List<Host> listHost;
    List<Host> hostListFilter ;
    private View view;
    private SearchView address_filter;

    private void onBindingView(){
        address_filter = view.findViewById(R.id.address_filter);
        rcv = view.findViewById(R.id.rcv);
        listHost = new ArrayList<>();
        homeFragment = new HomeFragment();
        adapter = new RcvAdapter(getActivity(), listHost, new IClickItemView() {
            @Override
            public void onClickView(Host p) {
                if(p !=  null){
                    Intent intent = new Intent(getActivity(),DetailHostActiviy.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object_host",p);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{

                }
            }
        });
        getListHost();
        layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(dividerItemDecoration);
    }
    private void bindingAction() {
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        address_filter.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        address_filter.setMaxWidth(Integer.MAX_VALUE);
        address_filter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if(!address_filter.isIconified()){
                    address_filter.setIconified(true);
                    return;
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()

    }



    private void getListHost(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("house_list");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    try {
                        Host host = postSnapshot.getValue(Host.class);
                        Log.e("host",host.toString());
                        listHost.add(host);
                    }catch (RuntimeException e){
                        Log.e("err",""+e);
                    }


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
//    private void setPushData(View view) {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("list_tro");
//        Host host = new Host(4,"Trọ Mê Ly","Thôn 9 Hòa Lạc","0988904123","3000000/tháng",5, 21.009258231611103, 105.53081170122883,"https://namidesign.vn/wp-content/uploads/2021/10/thiet-ke-nha-tro-4.jpg",10);
//        String pathObject = String.valueOf(host.getId());
//        myRef.child(pathObject).setValue(host, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(getActivity(), "add tro thanh cong", Toast.LENGTH_SHORT).show();
//            }
//        });{
//
//        }
//    }

    public HomeFragment() {
    }
    private void readDatabase(){
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_tro/1/Host");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Host value = dataSnapshot.getValue(Host.class);
               if(value != null){
//                   showw.setText(value.toString());
               }else{
//                   showw.setText("value.toString()");
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        onBindingView();
        bindingAction();
        return view;
    }

    public void reloadData() {
    }
}