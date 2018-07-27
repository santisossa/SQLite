package com.santiago.sqlite.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.santiago.sqlite.R;
import com.santiago.sqlite.adapter.UsuarioAdapter;
import com.santiago.sqlite.baseDatos.BaseDatos;
import com.santiago.sqlite.baseDatos.Constantes;
import com.santiago.sqlite.model.Usuario;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListarActivity extends AppCompatActivity {
    @BindView(R.id.lista)ListView lista;
    private UsuarioAdapter adapter;
    private ArrayList<Usuario> usuarios;
    private BaseDatos baseDatos;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        ButterKnife.bind(this);
        usuarios=getAllUsuarios();
        adapter= new UsuarioAdapter(this,R.layout.content_list,usuarios);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usuario=usuarios.get(position);
                Intent intent=new Intent(getApplicationContext(),DetalleUsuarioActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",usuario);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Usuario> getAllUsuarios() {
        baseDatos= new BaseDatos(getApplicationContext(),"usuarios.db",null,1);
        Cursor cursor=baseDatos.consultarUsuarios();
        usuarios= new ArrayList<>();
        do {
            usuario=new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex(Constantes._ID)));
            usuario.setNombre(cursor.getString(cursor.getColumnIndex(Constantes.NOMBRE)));
            usuario.setTelefono(cursor.getString(cursor.getColumnIndex(Constantes.TELEFONO)));
            usuarios.add(usuario);

        }while (cursor.moveToNext());
        return usuarios;
    }
}
