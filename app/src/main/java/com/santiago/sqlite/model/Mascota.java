package com.santiago.sqlite.model;

import java.io.Serializable;

public class Mascota implements Serializable{
    private Integer idDueño;
    private Integer id;
    private String nombre;
    private String raza;

    public Mascota() {
    }

    public Mascota(Integer idDueño, Integer id, String nombre, String raza) {
        this.idDueño = idDueño;
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
    }

    public Integer getIdDueño() {
        return idDueño;
    }

    public void setIdDueño(Integer idDueño) {
        this.idDueño = idDueño;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
