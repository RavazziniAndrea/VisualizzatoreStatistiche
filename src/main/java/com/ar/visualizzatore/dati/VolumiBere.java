package com.ar.visualizzatore.dati;

public class VolumiBere {
    public static final double BEER_LITERS = 0.4;
    public static final double SHOT_LITERS = 0.3;
    public static final double DRINK_LITERS = 0.4;
    public static final double SUPER_BOTTLE_LITERS = 1;
    public static final double WINE_BOTTLE_LITERS = 0.75;
    public static final double PONG_LITERS = 2.0;
    public static final double GLASS_LITERS = 0.3;

    private double qtaLetta;


    public VolumiBere() {
        this.qtaLetta = 0.0;
    }
    public VolumiBere(double qtaLetta) {
        this.qtaLetta = qtaLetta;
    }

    public double getQtaLetta() {
        return qtaLetta;
    }

    public void setQtaLetta(double qtaLetta) {
        this.qtaLetta = qtaLetta;
    }
}
