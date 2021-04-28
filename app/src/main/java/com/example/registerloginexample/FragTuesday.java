package com.example.registerloginexample;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registerloginexample.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragTuesday extends Fragment{

    private RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Track> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;


    public static FragTuesday newInstance(){
        FragTuesday fragTuesday = new FragTuesday();
        return fragTuesday;
    }

    private void initData(){

        arrayList.clear();
/*
        arrayList.add(new Track("R.drawable.pass_currect","check","d","체크"));
        arrayList.add(new Track("R.drawable.pass_right","check2","d","엑스1"));
        arrayList.add(new Track("R.drawable.pass_right","check3","d","엑스2"));
        arrayList.add(new Track("R.drawable.pass_right","check4","d","엑스3"));
        arrayList.add(new Track("R.drawable.pass_right","check5","d","엑스4"));
        arrayList.add(new Track("R.drawable.pass_right","check6","d","엑스5"));
        arrayList.add(new Track("R.drawable.pass_right","check7","d","엑스6"));
*/


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.frag_tuesday, container, false);

       // initData();


            Context context = rootView.getContext();
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            //recyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(),1));

            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);

            arrayList = new ArrayList<>();
            database = FirebaseDatabase.getInstance();
            databaseRef = database.getReference("Track");

            databaseRef.addListenerForSingleValueEvent(  new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    arrayList.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Track track = snapshot.getValue(Track.class);
                        arrayList.add(track);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("fragment1", String.valueOf(databaseError.toException()));//에러문 출력
                }
            });

        adapter = new CustomAdpter(arrayList,context);
        recyclerView.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public class RecyclerDecoration extends RecyclerView.ItemDecoration {
        private final int divHeight;

        public RecyclerDecoration(int divHeight) {
            this.divHeight = divHeight;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if(parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
                outRect.bottom = divHeight;
        }
    }
}
