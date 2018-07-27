package com.santiago.sqlite.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.santiago.sqlite.R;
import com.santiago.sqlite.model.Usuario;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalleUsuarioActivity extends AppCompatActivity {
    @BindView(R.id.idDetalle)TextView id;
    @BindView(R.id.nombreDetalle)TextView nombre;
    @BindView(R.id.telefonoDetalle)TextView telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);
        ButterKnife.bind(this);

        Bundle bundle=getIntent().getExtras();
        Usuario user;
        if (bundle!=null){
            user= (Usuario) bundle.getSerializable("user");
            id.setText(user.getId()+"");
            nombre.setText(user.getNombre());
            telefono.setText(user.getTelefono());
        }
    }
}
