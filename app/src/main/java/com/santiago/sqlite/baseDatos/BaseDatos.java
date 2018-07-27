package com.santiago.sqlite.baseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.santiago.sqlite.model.Mascota;
import com.santiago.sqlite.model.Usuario;

public class BaseDatos extends SQLiteOpenHelper {


    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Constantes.USUARIOS);
        sqLiteDatabase.execSQL(Constantes.MASCOTAS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_USUARIO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_MASCOTA);
        onCreate(sqLiteDatabase);
    }

    public boolean registrarUsuario(Usuario u){
            try {
                SQLiteDatabase bd=getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(Constantes._ID,u.getId());
                values.put(Constantes.NOMBRE,u.getNombre());
                values.put(Constantes.TELEFONO,u.getTelefono());

                return bd.insert(Constantes.TABLA_USUARIO,null,values)>0;

            }catch (Exception e){
                String error=e.getMessage();
                return false;

            }

    }

    public boolean registrarMascota(Mascota m){
        try {
            SQLiteDatabase bd=getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Constantes.MASCOTA,m.getNombre());
            values.put(Constantes.RAZA,m.getRaza());
            values.put(Constantes.ID_DUEÑO,m.getIdDueño());

            return bd.insert(Constantes.TABLA_MASCOTA,null,values)>0;

        }catch (Exception e){
            String error=e.getMessage();
            return false;

        }

    }

    public Cursor consultarUsuario(String id){
        try {
            SQLiteDatabase bd=getReadableDatabase();
            String[]campos={Constantes._ID,Constantes.NOMBRE,Constantes.TELEFONO};
            String[] parametro={id};
            Cursor cursor=bd.query(Constantes.TABLA_USUARIO,campos,Constantes._ID+"=?",parametro,null,null,null);
            if (cursor.moveToFirst()){
                return cursor;
            }else {
                return null;
            }
        }catch (Exception e){
            String error=e.getMessage();
            return null;

        }
    }

    public Cursor consultarUsuarios(){
        try {
            SQLiteDatabase bd=getReadableDatabase();
            Cursor cursor=bd.rawQuery("SELECT * FROM "+ Constantes.TABLA_USUARIO,null);
            if (cursor.moveToFirst()){
                return cursor;
            }else {
                return null;
            }
        }catch (Exception e){
            String error=e.getMessage();
            return null;

        }
    }
    public Cursor consultarMascotas(){
        try {
            SQLiteDatabase bd=getReadableDatabase();
            Cursor cursor=bd.rawQuery("SELECT * FROM "+ Constantes.TABLA_MASCOTA,null);
            if (cursor.moveToFirst()){
                return cursor;
            }else {
                return null;
            }
        }catch (Exception e){
            String error=e.getMessage();
            return null;

        }
    }

    public boolean actualizarUsuario(Usuario u,String id){
        try {
            SQLiteDatabase bd=getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Constantes.NOMBRE,u.getNombre());
            values.put(Constantes.TELEFONO,u.getTelefono());
            String[]Aid={id};

            return bd.update(Constantes.TABLA_USUARIO,values,Constantes._ID+"=?",Aid)>0;
        }catch (Exception e){
            String error=e.getMessage();
            return false;

        }
    }

    public boolean eliminarUsuario(String id){
        try {
            SQLiteDatabase bd=getWritableDatabase();
            String[]Aid={id};

            return bd.delete(Constantes.TABLA_USUARIO,Constantes._ID+"=?",Aid)>0;

        }catch (Exception e){
            String error=e.getMessage();
            return false;

        }
    }



}
