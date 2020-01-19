package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class KundeCardView extends AppCompatActivity implements RadioButtonDialog.SingleChoiceListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunde_card_view);
        getIncomingIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment singleChoice= new RadioButtonDialog();
                singleChoice.setCancelable(false);
                singleChoice.show(getSupportFragmentManager(),"Choose option:");
            }
        });
    }

    @Override
    public void onPoitiveButtonClicked(String[] options, int position) {
    }

    @Override
    public void onNegativeButtonClicked() {
    }

    private void getIncomingIntent() {
        String address = getIntent().getStringExtra("name");
        String preis = getIntent().getStringExtra("preis");
        String angebot = getIntent().getStringExtra("angebot");
        String anzahl = getIntent().getStringExtra("anzahl");

        TextView preisView = findViewById(R.id.preis);
        TextView angebotView = findViewById(R.id.angebot);
        TextView addresseView = findViewById(R.id.address);
        TextView anzahlView = findViewById(R.id.anzahl);
        TextView title = findViewById(R.id.addressImage);

        title.setText(address);
        addresseView.setText(address);
        preisView.setText(preis+" €");
        angebotView.setText(angebot);
        anzahlView.setText(anzahl);
    }
}
