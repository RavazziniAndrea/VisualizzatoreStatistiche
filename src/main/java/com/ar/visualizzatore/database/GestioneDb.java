package com.ar.visualizzatore.database;

import com.ar.visualizzatore.config.ConfigParser;
import com.ar.visualizzatore.dati.DatiStatistica;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class GestioneDb {

    private static final String DATABASE_FILE_NAME = "database.json";
    private static String json;
    private static JSONObject obj;

    public static List<Pair<String, String>> getDatabaseConnessioneConfig() throws IOException {
        checkSeCaricareJson();

        List<Pair<String, String>> connectionConfig = new ArrayList<>();
        JSONObject objConn = obj.getJSONObject("connessione");
        connectionConfig.add(new Pair<>("ip", objConn.getString("ip")));
        connectionConfig.add(new Pair<>("user", objConn.getString("user")));
        connectionConfig.add(new Pair<>("passwd", objConn.getString("passwd")));

        return connectionConfig;
    }

    public static List<Pair<Integer, String>> getQueryList() throws IOException {
        checkSeCaricareJson();

        List<Pair<Integer, String>> queryList = new ArrayList<>();
        JSONArray arrQuery = obj.getJSONArray("query");
        for(int i=0;i<arrQuery.length();i++){
            JSONObject objQuery = arrQuery.getJSONObject(i);
            queryList.add(new Pair<>(
                    objQuery.getInt("index"),
                    objQuery.getString("statement"))
            );
        }
        return queryList;
    }

    private static void checkSeCaricareJson() throws IOException {
        if(json == null || json.isEmpty() || obj == null){
            caricaJsonDaFile();
            obj = new JSONObject(json);
        }
    }

    private static void caricaJsonDaFile() throws IOException {
        try {
            json = new String(ConfigParser.class.getResourceAsStream(DATABASE_FILE_NAME).readAllBytes());
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    private Connection getConnessione(){
        Connection conn;

        conn= DriverManager.getConnection()

    }
}
