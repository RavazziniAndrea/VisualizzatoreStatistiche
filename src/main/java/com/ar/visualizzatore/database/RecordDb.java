package com.ar.visualizzatore.database;

import java.time.LocalDate;
import java.time.LocalTime;

public class RecordDb {
    private int qta;
    private String tipo;
    private LocalDate data;
    private LocalTime orario;

    public RecordDb(int qta, String tipo, LocalDate data, LocalTime orario) {
        this.qta = qta;
        this.tipo = tipo;
        this.data = data;
        this.orario = orario;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOrario() {
        return orario;
    }

    public void setOrario(LocalTime orario) {
        this.orario = orario;
    }
}
