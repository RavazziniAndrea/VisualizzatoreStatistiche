package com.ar.visualizzatore.dati;

import com.ar.visualizzatore.database.GestioneDb;
import com.ar.visualizzatore.database.RecordDb;

import java.util.List;

public class GestioneDati {

    private DatiTotali datiLetti = new DatiTotali();

    public GestioneDati() {}

    public void avviaThreadLetturaDati(){
        Thread t = new Thread(()->{
            while(true){

                List<RecordDb> records = GestioneDb.getRecordDbList();
                elaboraDati(records);
                //datiLetti.setChiusuraCasse(LocalTime.ofSecondOfDay(Duration.between(datiLetti.getChiusuraCasse(), LocalTime.now()).toSeconds()));


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

    private synchronized void elaboraDati(List<RecordDb> records) {
        datiLetti = new DatiTotali();
        for(RecordDb record : records){
            int qta = record.getQta();
            String tipo = record.getTipo();

            switch (tipo.toLowerCase().split(" ")[0]) {
                case "birra" -> {
                    if (tipo.toLowerCase().contains("pong"))
                        datiLetti.setLitriBevuti(datiLetti.getLitriBevuti() + (qta * VolumiBere.PONG_LITERS));
                        //TODO aggiungere beerpong fatti anche se poi non dovessimo avere pc al beerpong
                    else {
                        datiLetti.setLitriBevuti(datiLetti.getLitriBevuti() + (qta * VolumiBere.BEER_LITERS));
                        datiLetti.setBirreTotali(datiLetti.getBirreTotali() + qta);
                    }
                }
                case "drink" -> {
                    datiLetti.setLitriBevuti(datiLetti.getLitriBevuti() + (qta * VolumiBere.DRINK_LITERS));
                    datiLetti.setDrinkTotali(datiLetti.getDrinkTotali() + qta);
                }
                case "lambrusco", "rosso", "bianco", "prosecco" -> {
                    datiLetti.setLitriBevuti(datiLetti.getLitriBevuti() + (qta * VolumiBere.WINE_BOTTLE_LITERS));
                    datiLetti.setBottiglieVino(datiLetti.getBottiglieVino() + qta);
                }
                case "amaro", "bicchiere" ->
                        datiLetti.setLitriBevuti(datiLetti.getLitriBevuti() + (qta * VolumiBere.GLASS_LITERS));
            }
        }
    }

    public DatiTotali getDatiLetti() {
        return datiLetti;
    }

    public void setDatiLetti(DatiTotali datiLetti) {
        this.datiLetti = datiLetti;
    }
}
