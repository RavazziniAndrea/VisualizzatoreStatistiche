package com.ar.visualizzatore.dati;

import com.ar.visualizzatore.VisualizzatoreStatistiche;
import com.ar.visualizzatore.config.Config;
import javafx.util.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatiTotali {
    private Double litriBevuti;
    private Integer birreTotali;
    private Integer drinkTotali;
    private Integer bottiglieVino;
    private LocalTime chiusuraCasse;

    public DatiTotali() {
        this.litriBevuti = 0.0;
        this.birreTotali = 0;
        this.bottiglieVino = 0;
        this.drinkTotali = 0;
        this.chiusuraCasse = LocalTime.of(1,30,0);
    }

    public DatiTotali(double litriBevuti, int birreTotali, int drinkTotali, int bottiglieVino) {
        this.litriBevuti = litriBevuti;
        this.birreTotali = birreTotali;
        this.drinkTotali = drinkTotali;
        this.bottiglieVino = bottiglieVino;
    }

    public DatiTotali(double litriBevuti, int birreTotali, int drinkTotali, int bottiglieVino, LocalTime chiusuraCasse) {
        this.litriBevuti = litriBevuti;
        this.birreTotali = birreTotali;
        this.drinkTotali = drinkTotali;
        this.bottiglieVino = bottiglieVino;
        this.chiusuraCasse = chiusuraCasse;
    }

    public List<Pair<String, Object>> getListaValoriOrdinati(){
        Config config = VisualizzatoreStatistiche.getConfig();
        List<DatiStatistica> datiStatistiche = config.getDatiStatistiche();
        List<Pair<String, Object>> valori = new ArrayList<>();

        valori.add(new Pair<>(datiStatistiche.get(0).getId(), litriBevuti.intValue()));
        valori.add(new Pair<>(datiStatistiche.get(1).getId(), birreTotali));
        valori.add(new Pair<>(datiStatistiche.get(2).getId(), bottiglieVino));
        valori.add(new Pair<>(datiStatistiche.get(3).getId(), drinkTotali));
        valori.add(new Pair<>(datiStatistiche.get(4).getId(), chiusuraCasse));
        return valori;
    }

    public Double getLitriBevuti() {
        return litriBevuti;
    }
    public int getLitriBevutiInt() {
        return litriBevuti.intValue();
    }

    public void setLitriBevuti(double litriBevuti) {
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
        DatiTotali that = (DatiTotali) o;
        return litriBevuti == that.litriBevuti && birreTotali == that.birreTotali && drinkTotali == that.drinkTotali && bottiglieVino == that.bottiglieVino && Objects.equals(chiusuraCasse, that.chiusuraCasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(litriBevuti, birreTotali, drinkTotali, bottiglieVino, chiusuraCasse);
    }
}
