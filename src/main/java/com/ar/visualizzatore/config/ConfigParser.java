package com.ar.visualizzatore.config;

import com.ar.visualizzatore.dati.DatiStatistica;
import com.ar.visualizzatore.dati.DurataSchermata;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigParser {

    private static final String CONFIG_FILE_NAME = "config.json";

    private static String json;

    public static Config getConfig() throws IOException {
        return new Config(leggiStatistiche(), leggiDurata());
    }

    private static List<DatiStatistica> leggiStatistiche() throws IOException {
        if(json == null || json.isEmpty()) caricaJsonDaFile();
        List<DatiStatistica> statistiche = new ArrayList<>();
        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("statistiche");
        for(int i=0;i<arr.length();i++){
            JSONObject objStatistica = arr.getJSONObject(i);
            statistiche.add(new DatiStatistica(
                    objStatistica.getInt("index"),
                    objStatistica.getString("id"),
                    objStatistica.getString("testo"),
                    objStatistica.getString("tipo"),
                    objStatistica.getInt("font_size"),
                    objStatistica.getInt("font_size_valore"),
                    objStatistica.getBoolean("letto_db")
            ));
        }
        return statistiche;
    }

    private static DurataSchermata leggiDurata() throws IOException {
        if(json.isEmpty()) caricaJsonDaFile();
        JSONObject obj = new JSONObject(json);
        return new DurataSchermata(obj.getInt("durata_schermata_ms"));
    }

    private static void caricaJsonDaFile() throws IOException {
        try {
            json = new String(ConfigParser.class.getResourceAsStream(CONFIG_FILE_NAME).readAllBytes());
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new IOException(e);
        }
    }
}
