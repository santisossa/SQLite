package com.santiago.sqlite.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.santiago.sqlite.R;
import com.santiago.sqlite.baseDatos.BaseDatos;
import com.santiago.sqlite.baseDatos.Constantes;
import com.santiago.sqlite.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class ComboActivity extends AppCompatActivity {

    @BindView(R.id.spinner)Spinner spinner;
    @BindView(R.id.textViewId)TextView id;
    @BindView(R.id.textViewNombre)TextView nombre;
    @BindView(R.id.textViewTelefono)TextView telefono;
    ArrayList<String> list;
    ArrayList<Usuario>usuarioList;
    BaseDatos datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);
        ButterKnife.bind(this);
        datos= new BaseDatos(getApplicationContext(),"usuarios.db",null,1);
        llenarUsuarioList();

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long i) {
                if (position!=0){
                    id.setText(usuarioList.get(position-1).getId().toString());
                    nombre.setText(usuarioList.get(position-1).getNombre());
                    telefono.setText(usuarioList.get(position-1).getTelefono());
                }else {
                    id.setText("");
                    nombre.setText("");
                    telefono.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void llenarUsuarioList() {
        Cursor cursor=datos.consultarUsuarios();
        Usuario usuario=null;
        usuarioList= new ArrayList<>();
        do {
            usuario= new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex(Constantes._ID)));
            usuario.setNombre(cursor.getString(cursor.getColumnIndex(Constantes.NOMBRE)));
            usuario.setTelefono(cursor.getString(cursor.getColumnIndex(Constantes.TELEFONO)));
            usuarioList.add(usuario);
        }while (cursor.moveToNext());

        obtenerLista();
    }

    private void obtenerLista() {
        list= new ArrayList<>();
        list.add("seleccione");

        for (int i=0;i<usuarioList.size();i++){
            list.add(usuarioList.get(i).getId()+"/"+ usuarioList.get(i).getNombre());
        }
    }

}
