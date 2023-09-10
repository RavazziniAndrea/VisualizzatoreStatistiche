package com.ar.contatorevinopong;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dati {
    private Integer litriBevuti;
    private Integer birreTotali;
    private Integer drinkTotali;
    private Integer bottiglieVino;
    private LocalTime chiusuraCasse;

    public Dati() {
        this.litriBevuti = 0;
        this.birreTotali = 0;
        this.bottiglieVino = 0;
        this.chiusuraCasse = LocalTime.of(1,30,0);
    }

    public Dati(int litriBevuti, int birreTotali, int drinkTotali, int bottiglieVino) {
        this.litriBevuti = litriBevuti;
        this.birreTotali = birreTotali;
        this.drinkTotali = drinkTotali;
        this.bottiglieVino = bottiglieVino;
    }

    public Dati(int litriBevuti, int birreTotali, int drinkTotali, int bottiglieVino, LocalTime chiusuraCasse) {
        this.litriBevuti = litriBevuti;
        this.birreTotali = birreTotali;
        this.drinkTotali = drinkTotali;
        this.bottiglieVino = bottiglieVino;
        this.chiusuraCasse = chiusuraCasse;
    }

    public List<Object> getListaValoriOrdinati(){
        List<Object> valori = new ArrayList<>();
        valori.add(litriBevuti);
        valori.add(birreTotali);
        valori.add(bottiglieVino);
        valori.add(drinkTotali);
        valori.add(chiusuraCasse);
        return valori;
    }

    public int getLitriBevuti() {
        return litriBevuti;
    }

    public void setLitriBevuti(int litriBevuti) {
        this.litriBevuti = litriBevuti;
    }

    public int getBirreTotali() {
        return birreTotali;
    }

    public void setBirreTotali(int birreTotali) {
        this.birreTotali = birreTotali;
    }

    public int getDrinkTotali() {
        return drinkTotali;
    }

    public void setDrinkTotali(int drinkTotali) {
        this.drinkTotali = drinkTotali;
    }

    public int getBottiglieVino() {
        return bottiglieVino;
    }

    public void setBottiglieVino(int bottiglieVino) {
        this.bottiglieVino = bottiglieVino;
    }

    public LocalTime getChiusuraCasse() {
        return chiusuraCasse;
    }

    public void setChiusuraCasse(LocalTime chiusuraCasse) {
        this.chiusuraCasse = chiusuraCasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dati that = (Dati) o;
        return litriBevuti == that.litriBevuti && birreTotali == that.birreTotali && drinkTotali == that.drinkTotali && bottiglieVino == that.bottiglieVino && Objects.equals(chiusuraCasse, that.chiusuraCasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(litriBevuti, birreTotali, drinkTotali, bottiglieVino, chiusuraCasse);
    }
}
