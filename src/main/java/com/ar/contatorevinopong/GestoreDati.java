package com.ar.contatorevinopong;

import java.time.Duration;
import java.time.LocalTime;

public class GestoreDati {

    private Dati datiVisualizzati = new Dati();

    public GestoreDati() {}

    public void avviaThreadLetturaDati(){
        Thread t = new Thread(()->{
            for(int i=0;i<1000;i++){
                if(i>=999) i=0;
                datiVisualizzati.setLitriBevuti(i);
                datiVisualizzati.setBirreTotali(i+10);
                datiVisualizzati.setDrinkTotali(i+13);
                datiVisualizzati.setBottiglieVino(i+16);
                datiVisualizzati.setChiusuraCasse(LocalTime.ofSecondOfDay(Duration.between(datiVisualizzati.getChiusuraCasse(), LocalTime.now()).toSeconds()));

                System.out.println(datiVisualizzati.getChiusuraCasse().toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"thread lettura dati");
        t.setDaemon(true);
        t.start();
    }

    public Dati getDatiVisualizzati() {
        return datiVisualizzati;
    }

    public void setDatiVisualizzati(Dati datiVisualizzati) {
        this.datiVisualizzati = datiVisualizzati;
    }
}
