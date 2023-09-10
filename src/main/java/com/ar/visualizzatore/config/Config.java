package com.ar.visualizzatore.config;

import com.ar.visualizzatore.dati.DatiStatistica;
import com.ar.visualizzatore.dati.DurataSchermata;

import java.util.List;

public class Config {

    private List<DatiStatistica> datiStatistiche;

    private DurataSchermata durataSchermata;

    public Config(List<DatiStatistica> datiStatistiche, DurataSchermata durataSchermata) {
        this.datiStatistiche = datiStatistiche;
        this.durataSchermata = durataSchermata;
    }

    public List<DatiStatistica> getDatiStatistiche() {
        return datiStatistiche;
    }

    public void setDatiStatistiche(List<DatiStatistica> datiStatistiche) {
        this.datiStatistiche = datiStatistiche;
    }

    public DurataSchermata getDurataSchermata() {
        return durataSchermata;
    }

    public void setDurataSchermata(DurataSchermata durataSchermata) {
        this.durataSchermata = durataSchermata;
    }
}

