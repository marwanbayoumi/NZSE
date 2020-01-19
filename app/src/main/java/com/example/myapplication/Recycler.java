package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recycler extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Wohnungsobjekt> wohnungsobjekts;

    /*takes the Arraylist from readJson and sorts it by hits*/
    public static ArrayList<Wohnungsobjekt> sortByHits(ArrayList<Wohnungsobjekt> w) {
        Collections.sort(w, new Wohnungsobjekt.HitsComparator());
        return w;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        wohnungsobjekts = sortByHits(JsonHandler.readJson());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(wohnungsobjekts, this);
        recyclerView.setAdapter(adapter);
    }
//    public void openCardView(View view) {
//        Intent intent = new Intent(this, MarklerCardView.class);
//        startActivity(intent);
//    }

    /*Function for FAB*/
    public void addNewObject(View view) {
        Intent intent = new Intent(this, NewWohnugsObjektView.class);
        startActivity(intent);
    }
}
