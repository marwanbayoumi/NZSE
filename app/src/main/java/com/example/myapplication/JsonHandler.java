package com.example.myapplication;

import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public abstract class JsonHandler {

    final static ArrayList<Wohnungsobjekt> wohnungen = new ArrayList<>();
    static ArrayList<JSONObject> JSONs = new ArrayList<>();

    File myFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + "myFile.txt");

    /*adds new Wohnungsobjekt to ArrayList*/
    public void addWohnnung(Wohnungsobjekt wohnung) {
        wohnungen.add(wohnung);
    }

    /*reads the json file and returns an ArrayList<Wohnungsobjekt> which we can manipulate*/
    public static ArrayList<Wohnungsobjekt> readJson() {
        String fname = "myFile.txt";
        String alleausgaben = "";
        ArrayList<Wohnungsobjekt> read = new ArrayList<>();
        try {
            File myFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + fname);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String line;
            while ((line = myReader.readLine()) != null) {
                JSONArray jsonArray = new JSONArray(line);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONs.add(jsonObject);

                    String address = jsonObject.getString("addresse");
                    double price = jsonObject.getDouble("preis");
                    int AnzahlZimmer = jsonObject.getInt("anzahl der zimmer");
                    String angebot = jsonObject.getString("Type");
                    int hits = jsonObject.getInt("Hits");

                    Wohnungsobjekt deserializiedWohnung = new Wohnungsobjekt(address, AnzahlZimmer, price, angebot, hits);
//                    Wohnungsobjekt deserializiedWohnung = new Wohnungsobjekt(address, AnzahlZimmer, price, angebot);
                    read.add(deserializiedWohnung);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return read;
    }

    /*creates a timer that calls our readJson method every 30 seconds*/
    public static void timer() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                readJson();
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long period = 60000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    /*writes new entries to the json file then clears them*/
    public void writeJson() throws JSONException, IOException {
        try {
            FileOutputStream fOut = new FileOutputStream(myFile, true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            JSONArray jsonarray = new JSONArray();
//            int index = 0;

            for (Wohnungsobjekt i : wohnungen) {
                JSONObject object = new JSONObject();
//                object.put("id", index++);
                object.put("addresse", i.getAddresse());
                object.put("preis", i.getPreis());
                object.put("anzahl der zimmer", i.getZimmer_anzahl());
                object.put("Type", i.getDasAngebot());
                object.put("Hits", i.getHits());

                jsonarray.put(object);
            }
            myOutWriter.append(jsonarray.toString());
            myOutWriter.append('\n');
            myOutWriter.close();
            fOut.close();
            wohnungen.clear();
            System.out.println("written successfully");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

//    public static void updateJSON(JSONObject jsonObject, int hits) throws JSONException {
//        int id = jsonObject.getInt("id");
//        String fname = "myFile.txt";
//        try {
//            File myFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + fname);
//            FileInputStream fIn = new FileInputStream(myFile);
//            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
//            String line;
//            while ((line = myReader.readLine()) != null) {
//                JSONArray jsonArray = new JSONArray(line);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObj = jsonArray.getJSONObject(i);
//                    if(id == jsonObj.getInt("id")){
//                        jsonObj.put("Hits",hits);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void print() {
        System.out.println(JSONs.size());
    }

    /*takes the Arraylist from readJson and sorts it by price*/
    public static ArrayList<Wohnungsobjekt> sortByPrice(ArrayList<Wohnungsobjekt> w) {
        Collections.sort(w, new Wohnungsobjekt.PriceComparator());
        return w;
    }

    /*takes the Arraylist from readJson and sorts it by hits*/
    public static ArrayList<Wohnungsobjekt> sortByHits(ArrayList<Wohnungsobjekt> w) {
        Collections.sort(w, new Wohnungsobjekt.HitsComparator());
        return w;
    }
}