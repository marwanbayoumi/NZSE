package com.example.myapplication;

import org.json.JSONException;

import java.io.IOException;
import java.util.Comparator;

public class Wohnungsobjekt extends JsonHandler {
    private String addresse;
    private int zimmer_anzahl;
    private double preis;
    private int hits;
    private final String angebotTyp[] = {"Mieten", "Verkauf"};
    private String dasAngebot;

    public Wohnungsobjekt(String addresse, int zimmer_anzahl, double preis) throws IOException, JSONException {
        this.addresse = addresse;
        this.zimmer_anzahl = zimmer_anzahl;
        this.preis = preis;
        super.addWohnnung(this);
    }

    public Wohnungsobjekt(String addresse, int zimmer_anzahl, double preis, String dasAngebot, int hits) throws IOException, JSONException {
        this.addresse = addresse;
        this.zimmer_anzahl = zimmer_anzahl;
        this.preis = preis;
        this.dasAngebot = dasAngebot;
        this.hits = hits;
    }

    public void writeJson() throws IOException, JSONException {
        super.writeJson();
    }

    /*    public void readJson() {
        super.readJson();
    }*/

    /*public void timer() {
        super.timer();
    }*/
    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getZimmer_anzahl() {
        return zimmer_anzahl;
    }

    public void setZimmer_anzahl(int zimmer_anzahl) {
        this.zimmer_anzahl = zimmer_anzahl;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void setDasAngebot(int typ) {
        this.dasAngebot = this.angebotTyp[typ];
    }

    public String getDasAngebot() {
        return dasAngebot;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

//    public void countHits() {
//        this.hits++;
//    }

    static class PriceComparator implements Comparator<Wohnungsobjekt> {
        @Override
        public int compare(Wohnungsobjekt w1, Wohnungsobjekt w2) {
            return (int) w1.getPreis() - (int) w2.getPreis();
        }
    }

    static class HitsComparator implements Comparator<Wohnungsobjekt> {
        @Override
        public int compare(Wohnungsobjekt w1, Wohnungsobjekt w2) {
            return w1.getHits() - w2.getHits();
        }
    }
}