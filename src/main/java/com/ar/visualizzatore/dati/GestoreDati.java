package com.ar.visualizzatore.dati;

import com.ar.visualizzatore.database.GestioneDb;
import com.ar.visualizzatore.database.RecordDb;
import com.ar.visualizzatore.dati.Dati;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class GestoreDati {

    private Dati datiLetti = new Dati();

    public GestoreDati() {}

    public void avviaThreadLetturaDati(){
        Thread t = new Thread(()->{
            while(true){

                List<RecordDb> records = GestioneDb.getRecordDbList();

                datiLetti.setLitriBevuti(i);
                datiLetti.setBirreTotali(i+10);
                datiLetti.setDrinkTotali(i+13);
                datiLetti.setBottiglieVino(i+16);
                //datiLetti.setChiusuraCasse(LocalTime.ofSecondOfDay(Duration.between(datiLetti.getChiusuraCasse(), LocalTime.now()).toSeconds()));
                System.out.println(datiLetti.getChiusuraCasse().toString());


                try {
                    Thread.sleep(2000);
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
