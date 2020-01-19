package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class  MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendToCustomer(View view) {
        Intent intent = new Intent(this, Kunde_Recycler.class);
        startActivity(intent);
    }

    public void sendToAgent(View view) {
        Intent intent = new Intent(this, Recycler.class);
        startActivity(intent);
    }
}
