package com.ar.visualizzatore;

import com.ar.visualizzatore.dati.Dati;

import java.time.Duration;
import java.time.LocalTime;

public class GestoreDati {

    private Dati datiLetti = new Dati();

    public GestoreDati() {}

    public void avviaThreadLetturaDati(){
        Thread t = new Thread(()->{


            /*
                TODO
                Devo lavorare da qui per rendere accessibili le query ed estrarre i dati
             */


            for(int i=0;i<1000;i++){
                if(i>=999) i=0;
                datiLetti.setLitriBevuti(i);
                datiLetti.setBirreTotali(i+10);
                datiLetti.setDrinkTotali(i+13);
                datiLetti.setBottiglieVino(i+16);
                datiLetti.setChiusuraCasse(LocalTime.ofSecondOfDay(Duration.between(datiLetti.getChiusuraCasse(), LocalTime.now()).toSeconds()));

                System.out.println(datiLetti.getChiusuraCasse().toString());
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

    public Dati getDatiLetti() {
        return datiLetti;
    }

    public void setDatiLetti(Dati datiLetti) {
        this.datiLetti = datiLetti;
    }
}
