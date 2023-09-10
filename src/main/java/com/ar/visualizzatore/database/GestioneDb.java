package com.ar.visualizzatore.database;

import com.ar.visualizzatore.config.ConfigParser;
import com.ar.visualizzatore.dati.DatiStatistica;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestioneDb {

    private Map<String, String> connMap;
    private List<Pair<Integer, String>> queryList;

    public Object eseguiQuery(){
        try(Connection c = getConnessione()) {
            try(Statement st = c.createStatement()){
                for(Pair p : queryList){
                    try(ResultSet rs = st.executeQuery(String.valueOf(p.getValue()))){
                        while(rs.next()){
//                            conto e calcolo
                        }
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Connection getConnessione() throws IOException, SQLException {
        Map<String, String> connMap = DbConfig.getDatabaseConnessioneConfig();
        List<Pair<Integer, String>> queryList = DbConfig.getQueryList();

        return DriverManager.getConnection("jdbc:postgresql://"+connMap.get("ip"), connMap.get("user"), connMap.get("passwd"));
    }
}
