package com.santiago.sqlite.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.santiago.sqlite.R;
import com.santiago.sqlite.baseDatos.BaseDatos;
import com.santiago.sqlite.baseDatos.Constantes;
import com.santiago.sqlite.model.Mascota;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistroMascotaActivity extends AppCompatActivity {

    @BindView(R.id.editTextnomMascota)EditText nomMascota;
    @BindView(R.id.editTextRazaMascota)EditText raza;
    @BindView(R.id.spinnerDueño)Spinner spinner;
    @BindView(R.id.btnRegistrarMascota)Button registar;

    private BaseDatos datos;
    private Mascota mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);
        ButterKnife.bind(this);
        datos= new BaseDatos(getApplicationContext(),"usuarios.db",null,1);


        Cursor cursor=datos.consultarUsuarios();
        String[] Campos={Constantes._ID,Constantes.NOMBRE,Constantes.TELEFONO};
        int[] vista={R.id.documento,R.id.nombre,R.id.telefono};

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.content_list,cursor,Campos,vista);
        spinner.setAdapter(adapter);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota=new Mascota();
                mascota.setNombre(nomMascota.getText().toString());
                mascota.setRaza(raza.getText().toString());
                int idDueño= (int) spinner.getSelectedItemId();
                mascota.setIdDueño(idDueño);
                if (datos.registrarMascota(mascota)){
                    Toast.makeText(RegistroMascotaActivity.this, "Registrada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegistroMascotaActivity.this, "No Registrada", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
