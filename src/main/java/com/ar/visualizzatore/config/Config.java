package com.ar.visualizzatore.config;

import java.util.List;
import java.util.Map;

public class Config {

    private List<DatiStatistica> datiStatistiche;
    Map<String, Integer> dimensioni;
    private DurataSchermata durataSchermata;

    public Config(List<DatiStatistica> datiStatistiche, Map<String, Integer> dimensioni, DurataSchermata durataSchermata) {
        this.datiStatistiche = datiStatistiche;
        this.dimensioni = dimensioni;
        this.durataSchermata = durataSchermata;
    }

    public List<DatiStatistica> getDatiStatistiche() {
        return datiStatistiche;
    }

    public void setDatiStatistiche(List<DatiStatistica> datiStatistiche) {
        this.datiStatistiche = datiStatistiche;
    }

    public Map<String, Integer> getDimensioni() {
        return dimensioni;
    }

    public void setDimensioni(Map<String, Integer> dimensioni) {
        this.dimensioni = dimensioni;
    }

    public DurataSchermata getDurataSchermata() {
        return durataSchermata;
    }

    public void setDurataSchermata(DurataSchermata durataSchermata) {
        this.durataSchermata = durataSchermata;
    }
}

