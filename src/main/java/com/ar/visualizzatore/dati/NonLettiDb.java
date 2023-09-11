package com.ar.visualizzatore.dati;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class NonLettiDb {
    static Map<String, Object> nonLettiDbMap = new HashMap<>();


    public static Map<String, Object> getNonLettiDbMap() {
        return nonLettiDbMap;
    }

    public void setNonLettiDbMap(Map<String, Object> nonLettiDbMap) {
        this.nonLettiDbMap = nonLettiDbMap;
    }
}
