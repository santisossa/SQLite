package com.santiago.sqlite.baseDatos;

import android.provider.BaseColumns;

public class Constantes implements BaseColumns {

    public final static String TABLA_USUARIO="Usuarios";
    public final static String NOMBRE="nombre";
    public final static String TELEFONO="telefono";

    public final static String TABLA_MASCOTA="mascotas";
    public final static String MASCOTA="nombre";
    public final static String RAZA="raza";
    public final static String ID_DUEÑO="_id_dueño";

    public final static String USUARIOS="CREATE TABLE "+Constantes.TABLA_USUARIO +
            "("+ Constantes._ID + " INTEGER PRIMARY KEY,"
            + Constantes.NOMBRE + " TEXT,"
            + Constantes.TELEFONO + " TEXT"+")";

    public final static String MASCOTAS="CREATE TABLE "+Constantes.TABLA_MASCOTA +
            "("+ Constantes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Constantes.MASCOTA + " TEXT,"
            + Constantes.RAZA + " TEXT,"
            + Constantes.ID_DUEÑO + " INTEGER)";
}
