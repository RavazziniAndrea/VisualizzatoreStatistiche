package com.ar.contatorevinopong;

import Exceptions.LetturaConfigException;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Configurazione {

    public static final String CONFIG_PATH = "config.properties";

    private List<String> statistiche;
    private LocalTime time;
    private int durataSlide;

    public Configurazione(List<String> statistiche, LocalTime time, int durataSlide) {
        this.statistiche = statistiche;
        this.time = time;
        this.durataSlide = durataSlide;
    }

    public static Configurazione leggiConfigFile() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIG_PATH));
        List<String> statistiche = Arrays.asList(properties.getProperty("statistiche").split(","));
        try{
            int durata = Integer.parseInt(properties.getProperty("durata_slide_ms"));
            int ora     = Integer.parseInt(properties.getProperty("ora_chiusura_casse"));
            int minuti  = Integer.parseInt(properties.getProperty("minuti_chiusura_casse"));
            LocalTime tempo = LocalTime.of(ora,minuti);
            return new Configurazione(statistiche, tempo, durata);
        } catch (NumberFormatException e){
            throw new LetturaConfigException(e);
        }
    }

    public List<String> getStatistiche() {
        return statistiche;
    }

    public void setStatistiche(List<String> statistiche) {
        this.statistiche = statistiche;
    }

    public LocalTime getTempo() {
        return time;
    }

    public void setTempo(LocalTime time) {
        this.time = time;
    }

    public int getDurataSlide() {
        return durataSlide;
    }

    public void setDurataSlide(int durataSlide) {
        this.durataSlide = durataSlide;
    }

    @Override
    public String toString() {
        return "Configurazione{" +
                "statistiche=" + statistiche +
                ", time=" + time +
                ", durataSlide=" + durataSlide +
                '}';
    }
}
