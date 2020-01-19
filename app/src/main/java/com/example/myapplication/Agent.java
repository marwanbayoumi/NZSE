package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Agent extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "MARKLER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
    }

//    public void openCardView(View view) {
//        Intent intent = new Intent(this, MarklerCardView.class);
//        startActivity(intent);
//    }


//    public void addNewObject(View view) {
//        Intent intent = new Intent(this, NewWohnugsObjektView.class);
//        startActivity(intent);
//    }
}
