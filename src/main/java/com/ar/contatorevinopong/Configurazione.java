package com.ar.contatorevinopong;

import Exceptions.LetturaConfigException;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Configurazione {

    public static final String CONFIG_PATH = "config.properties";

    private List<String> statistiche;
    private List<Integer> statisticheFont;
    private int durataSlide;

    public Configurazione(List<String> statistiche, List<Integer> statisticheFont, int durataSlide) {
        this.statistiche = statistiche;
        this.statisticheFont = statisticheFont;
        this.durataSlide = durataSlide;
    }

    public static Configurazione leggiConfigFile() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIG_PATH));
        List<String> statistiche = Arrays.asList(properties.getProperty("statistiche").split(","));
        try{
            List<Integer> statisticheFont = getStatisticheFontIntegers(properties);
            int durata = Integer.parseInt(properties.getProperty("durata_slide_ms"));
            return new Configurazione(statistiche, statisticheFont, durata);
        } catch (NumberFormatException e){
            throw new LetturaConfigException(e);
        }
    }

    private static List<Integer> getStatisticheFontIntegers(Properties properties) {
        List<Integer> statisticheFont = new ArrayList<>();
        String[] statFontStr = properties.getProperty("statistiche_font").split(",");
        for(String s : statFontStr){
            statisticheFont.add(Integer.parseInt(s));
        }
        return statisticheFont;
    }

    public List<String> getStatistiche() {
        return statistiche;
    }

    public void setStatistiche(List<String> statistiche) {
        this.statistiche = statistiche;
    }

    public List<Integer> getStatisticheFont() {
        return statisticheFont;
    }

    public void setStatisticheFont(List<Integer> statisticheFont) {
        this.statisticheFont = statisticheFont;
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
                ", durataSlide=" + durataSlide +
                '}';
    }
}
