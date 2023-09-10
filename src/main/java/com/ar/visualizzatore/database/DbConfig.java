package com.ar.visualizzatore.database;

import com.ar.visualizzatore.config.ConfigParser;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbConfig {
    private static final String DATABASE_FILE_NAME = "database.json";
    private static String json;
    private static JSONObject obj;

    public static Map<String, String> getDatabaseConnessioneConfig() throws IOException {
        checkSeCaricareJson();

        Map<String, String> connectionConfig = new HashMap<>();
        JSONObject objConn = obj.getJSONObject("connessione");
        connectionConfig.put("ip", objConn.getString("ip"));
        connectionConfig.put("user", objConn.getString("user"));
        connectionConfig.put("passwd", objConn.getString("passwd"));

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

}
