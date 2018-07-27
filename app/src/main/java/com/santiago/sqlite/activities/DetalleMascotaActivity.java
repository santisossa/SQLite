package com.santiago.sqlite.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.santiago.sqlite.R;
import com.santiago.sqlite.baseDatos.BaseDatos;
import com.santiago.sqlite.baseDatos.Constantes;
import com.santiago.sqlite.model.Mascota;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalleMascotaActivity extends AppCompatActivity {
    @BindView(R.id.txt_idMascota)TextView idMascota;
    @BindView(R.id.txt_nomMascota)TextView nomMascota;
    @BindView(R.id.txt_Raza)TextView raza;
    @BindView(R.id.txt_idDueño)TextView idDueño;
    @BindView(R.id.txt_nomDueño)TextView nomDueño;
    @BindView(R.id.txt_telefono)TextView telefono;
    private BaseDatos datos;
    private Mascota mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);
        ButterKnife.bind(this);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
           mascota= (Mascota) bundle.getSerializable("mascota");
            idMascota.setText(mascota.getId().toString());
            nomMascota.setText(mascota.getNombre());
            raza.setText(mascota.getRaza());
            consultarDueño(mascota.getIdDueño().toString());
        }
    }

    private void consultarDueño(String idDueño) {
        datos= new BaseDatos(getApplicationContext(),"usuarios.db",null,1);
        Cursor cursor=datos.consultarUsuario(idDueño);
            String id= String.valueOf(cursor.getInt(cursor.getColumnIndex(Constantes._ID)));
            this.idDueño.setText(id);
            nomDueño.setText(cursor.getString(cursor.getColumnIndex(Constantes.NOMBRE)));
            telefono.setText(cursor.getString(cursor.getColumnIndex(Constantes.TELEFONO)));
            cursor.close();

    }
}
