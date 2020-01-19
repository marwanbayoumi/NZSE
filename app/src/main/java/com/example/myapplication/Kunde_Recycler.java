package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class Kunde_Recycler extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Wohnungsobjekt> wohnungsobjekts;

    /*takes the Arraylist from readJson and sorts it by price*/
    public static ArrayList<Wohnungsobjekt> sortByPrice(ArrayList<Wohnungsobjekt> w) {
        Collections.sort(w, new Wohnungsobjekt.PriceComparator());
        return w;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunde__recycler);

        wohnungsobjekts = sortByPrice(JsonHandler.readJson());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewKunde);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapterKunde(wohnungsobjekts, this);
        recyclerView.setAdapter(adapter);
    }
    public void openCardView(View view) {
        Intent intent = new Intent(this, KundeCardView.class);
        startActivity(intent);
    }
}
