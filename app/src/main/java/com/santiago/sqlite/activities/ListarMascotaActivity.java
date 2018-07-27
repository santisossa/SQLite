package com.santiago.sqlite.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.santiago.sqlite.R;
import com.santiago.sqlite.adapter.MascotaAdapter;
import com.santiago.sqlite.adapter.UsuarioAdapter;
import com.santiago.sqlite.baseDatos.BaseDatos;
import com.santiago.sqlite.baseDatos.Constantes;
import com.santiago.sqlite.model.Mascota;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListarMascotaActivity extends AppCompatActivity {
    @BindView(R.id.listaMascotas)ListView lista;
    private BaseDatos datos;
    private ArrayList<Mascota> mascotas;
    private Mascota mascota;
    private MascotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_mascota);
        ButterKnife.bind(this);

        mascotas=getMascota();
        adapter=new MascotaAdapter(this,R.layout.content_list_mascota,mascotas);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mascota=mascotas.get(position);
                Intent intent=new Intent(getApplicationContext(),DetalleMascotaActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("mascota",mascota);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    private ArrayList<Mascota> getMascota() {
        datos= new BaseDatos(getApplicationContext(),"usuarios.db",null,1);
        Cursor cursor=datos.consultarMascotas();
        mascotas= new ArrayList<>();
        do {
            mascota=new Mascota();
            mascota.setId(cursor.getInt(cursor.getColumnIndex(Constantes._ID)));
            mascota.setNombre(cursor.getString(cursor.getColumnIndex(Constantes.MASCOTA)));
            mascota.setRaza(cursor.getString(cursor.getColumnIndex(Constantes.RAZA)));
            mascota.setIdDueño(cursor.getInt(cursor.getColumnIndex(Constantes.ID_DUEÑO)));
            mascotas.add(mascota);
        }while (cursor.moveToNext());

        return mascotas;

    }
}
