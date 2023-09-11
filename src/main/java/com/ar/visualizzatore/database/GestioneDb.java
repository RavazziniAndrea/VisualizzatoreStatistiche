package com.ar.visualizzatore.database;

import com.ar.visualizzatore.config.ConfigParser;
import com.ar.visualizzatore.dati.DatiStatistica;
import com.ar.visualizzatore.dati.QtaBere;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestioneDb {

    private Map<String, String> connMap;
    private static List<Pair<Integer, String>> queryList;

    public static List<RecordDb> getRecordDbList(){
        return eseguiQuery();
    }

    private static List<RecordDb> eseguiQuery(){
        List<RecordDb> records = new ArrayList<>();
        try(Connection c = getConnessione()) {
            for(Pair<Integer, String> p : queryList){ //Che in realtà la query è solo una
                try(ResultSet rs = c.createStatement().executeQuery(String.valueOf(p.getValue()))){
                    QtaBere qtaBere = new QtaBere();
                    while(rs.next()){
                        int qta = rs.getInt("qta");
                        String tipo = rs.getString("tipo");
                        LocalDate data = null;
                        if(rs.getDate("data") != null) {
                            data = rs.getDate("data").toLocalDate();
                        }
                        LocalTime orario = null;
                        if(rs.getTime("ora") != null) {
                            orario = rs.getTime("ora").toLocalTime();
                        }

                        records.add(new RecordDb(qta,tipo,data,orario));
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    private static Connection getConnessione() throws IOException, SQLException {
        Map<String, String> connMap = DbConfig.getDatabaseConnessioneConfig();
        queryList = DbConfig.getQueryList();

        Connection c = DriverManager.getConnection("jdbc:postgresql://"+connMap.get("ip")+":"+connMap.get("port")+"/"+ connMap.get("table"),connMap.get("user"),connMap.get("passwd"));
        c.setAutoCommit(false);
        c.setReadOnly(true);
        c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return c;    }
}
