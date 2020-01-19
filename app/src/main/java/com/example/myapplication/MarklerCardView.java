package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

public class MarklerCardView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markler_card_view);
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        String address = getIntent().getStringExtra("name");
        String preis = getIntent().getStringExtra("preis");
        String angebot = getIntent().getStringExtra("angebot");
        String anzahl = getIntent().getStringExtra("anzahl");
        String hits = getIntent().getStringExtra("hits");

        TextView preisView = findViewById(R.id.preis);
        TextView angebotView = findViewById(R.id.angebot);
        TextView addresseView = findViewById(R.id.address);
        TextView anzahlView = findViewById(R.id.anzahl);
        TextView hitsView = findViewById(R.id.hits);
        TextView title = findViewById(R.id.addressImage);

        title.setText(address);
        addresseView.setText(address);
        preisView.setText(preis+" €");
        angebotView.setText(angebot);
        anzahlView.setText(anzahl);
        hitsView.setText(hits);
    }

}
