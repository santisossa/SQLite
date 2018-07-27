package com.santiago.sqlite.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.santiago.sqlite.R;
import com.santiago.sqlite.baseDatos.BaseDatos;
import com.santiago.sqlite.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
private EditText id,nombre,telefono;
private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        id=findViewById(R.id.editTextIdConsultar);
        nombre=findViewById(R.id.editTextNombreConsultar);
        telefono=findViewById(R.id.editTextTelefonoConsultar);
        btn=findViewById(R.id.btnRegistrar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos baseDatos= new BaseDatos(getApplicationContext(),"usuarios.db",null,1);
                Usuario usuario=
                        new Usuario(Integer.parseInt(id.getText().toString()),nombre.getText().toString(),telefono.getText().toString());
                if (baseDatos.registrarUsuario(usuario)){
                    Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_SHORT).show();
                    id.getText().clear();
                    nombre.getText().clear();
                    telefono.getText().clear();
                }else{
                    Toast.makeText(getApplicationContext(), "No Registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
