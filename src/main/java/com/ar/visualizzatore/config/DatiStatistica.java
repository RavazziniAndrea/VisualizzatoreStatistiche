package com.ar.visualizzatore.config;

public class DatiStatistica {

    private int index;
    private String id;
    private String testo;
    private String tipo;
    private int fontSize;
    private int fontSizeValore;
    private boolean lettoDb;

    public DatiStatistica(int index, String id, String testo, String tipo, int fontSize, int fontSizeValore, boolean lettoDb) {
        this.index = index;
        this.id = id;
        this.testo = testo;
        this.tipo = tipo;
        this.fontSize = fontSize;
        this.fontSizeValore = fontSizeValore;
        this.lettoDb = lettoDb;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSizeValore() {
        return fontSizeValore;
    }

    public void setFontSizeValore(int fontSizeValore) {
        this.fontSizeValore = fontSizeValore;
    }

    public boolean isLettoDb() {
        return lettoDb;
    }

    public void setLettoDb(boolean lettoDb) {
        this.lettoDb = lettoDb;
    }
}
