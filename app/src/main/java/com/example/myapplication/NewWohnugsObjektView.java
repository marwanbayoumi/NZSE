package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import java.io.IOException;

public class NewWohnugsObjektView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wohnugs_objekt_view);
    }

    public void createNewObjekt(View view) throws IOException, JSONException {
        EditText editText = (EditText) findViewById(R.id.address);
        String address = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.zimmerAn);
        String zimmerAnzahl = editText2.getText().toString();
        int AnzahlZimmer = Integer.parseInt(zimmerAnzahl);
        EditText editText3 = (EditText) findViewById(R.id.preis);
        String preis = editText3.getText().toString();
        double price = Double.parseDouble(preis);
        CheckBox checkBox = findViewById(R.id.checkBox);
        boolean check = checkBox.isChecked();
        Wohnungsobjekt wohnungsobjekt = new Wohnungsobjekt(address, AnzahlZimmer, price);
        if(check){
            wohnungsobjekt.setDasAngebot(1);
        } else{
            wohnungsobjekt.setDasAngebot(0);
        }
        wohnungsobjekt.writeJson();
        JsonHandler.print();
//        wohnungsobjekt.timer();
//        wohnungsobjekt.readJson();
        Toast.makeText(this, "Objekt Erfolgreich hinzugefügt" , Toast.LENGTH_SHORT).show();

        this.finish();
    }
}
