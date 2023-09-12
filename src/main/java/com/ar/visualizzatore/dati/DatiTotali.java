package com.ar.visualizzatore.dati;

import Exceptions.CampiNonUguali;
import com.ar.visualizzatore.VisualizzatoreStatistiche;
import com.ar.visualizzatore.config.Config;
import com.ar.visualizzatore.config.DatiStatistica;
import javafx.util.Pair;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DatiTotali {
    private Double litriBevuti;
    private Integer birreTotali;
    private Integer drinkTotali;
    private Integer bottiglieVino;
    private LocalTime chiusuraCasse;
    private Integer litri2022;
    private Integer giorniNatale;

    public DatiTotali() {
        this.litriBevuti = 0.0;
        this.birreTotali = 0;
        this.bottiglieVino = 0;
        this.drinkTotali = 0;
        this.chiusuraCasse = (NonLettiDb.getNonLettiDbMap().get("ChiusuraCasse") == null)
                ? null
                : LocalTime.parse(NonLettiDb.getNonLettiDbMap().get("ChiusuraCasse").toString());
        this.litri2022 = (NonLettiDb.getNonLettiDbMap().get("Litri2022") == null)
                ? null
                : Integer.parseInt((String) NonLettiDb.getNonLettiDbMap().get("Litri2022"));
        this.giorniNatale = (NonLettiDb.getNonLettiDbMap().get("GiorniNatale") == null)
                ? null
                : calcolaGiorniNatale();
    }


    /* TODO!!!!!!!!!
    * Ogni volta che si aggiunge un campo, va messo anche nella mappa!!!!!
    * */
    private Map<String, Object> getMappaCampi() throws CampiNonUguali {
        Map<String, Object> campiClasse = new HashMap<>();
        campiClasse.put("LitriBevuti", litriBevuti.intValue());
        campiClasse.put("BirreTotali", birreTotali);
        campiClasse.put("BottiglieVino", bottiglieVino);
        campiClasse.put("DrinkTotali", drinkTotali);
        campiClasse.put("ChiusuraCasse", chiusuraCasse);
        campiClasse.put("Litri2022", litri2022);
        campiClasse.put("GiorniNatale", giorniNatale);

        Field[] campi = DatiTotali.class.getDeclaredFields();
        if(campi.length != campiClasse.size()) throw new CampiNonUguali("Campi non inseriti nella mappa");

        return campiClasse;
    }

    public Map<Integer, Object> getMappaValori() throws CampiNonUguali {
        Config config = VisualizzatoreStatistiche.getConfig();
        List<DatiStatistica> datiStatistiche = config.getDatiStatistiche();

        Map<Integer, Object> val = new HashMap<>();
        Map<String, Object> campi = getMappaCampi();
        for(int i=0;i<datiStatistiche.size();i++){
            val.put(i, campi.get(datiStatistiche.get(i).getId()));
        }
        return val;
    }


    private Integer calcolaGiorniNatale() {
        LocalDate natale = LocalDate.of(2023, 12, 25);
        int giornoAnnoNatale = natale.getDayOfYear();
        return giornoAnnoNatale - LocalDate.now().getDayOfYear() - 1;
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

    public Integer getLitri2022() {
        return litri2022;
    }

    public void setLitri2022(Integer litri2022) {
        this.litri2022 = litri2022;
    }

    public Integer getGiorniNatale() {
        return giorniNatale;
    }

    public void setGiorniNatale(Integer giorniNatale) {
        this.giorniNatale = giorniNatale;
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
