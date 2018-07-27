package com.santiago.sqlite.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.santiago.sqlite.R;
import com.santiago.sqlite.baseDatos.BaseDatos;
import com.santiago.sqlite.baseDatos.Constantes;
import com.santiago.sqlite.model.Usuario;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultarActivity extends AppCompatActivity {
    @BindView(R.id.editTextIdConsultar) EditText id;
    @BindView(R.id.editTextNombreConsultar) EditText nom;
    @BindView(R.id.editTextTelefonoConsultar) EditText tel;
    BaseDatos baseDatos;
    @OnClick(R.id.btnConsultar)void consultar(){
        try {
            Cursor c=baseDatos.consultarUsuario(id.getText().toString());
            nom.setText(c.getString(c.getColumnIndex(Constantes.NOMBRE)));
            tel.setText(c.getString(c.getColumnIndex(Constantes.TELEFONO)));
            c.close();
            baseDatos.close();
        }catch (Exception e){
            Toast.makeText(this, "no existe el documento", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar() {
        id.getText().clear();
        nom.getText().clear();
        tel.getText().clear();
    }

    @OnClick(R.id.btnActualizar)void actualizar(){
        Usuario usuario= new Usuario();
        usuario.setNombre(nom.getText().toString());
        usuario.setTelefono(tel.getText().toString());
        if (baseDatos.actualizarUsuario(usuario,id.getText().toString())){
            Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
            limpiar();
        }else {
            Toast.makeText(this, "No Actualizado", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();


    }
    @OnClick(R.id.btnEliminar)void eliminar(){
        if (baseDatos.eliminarUsuario(id.getText().toString())){
            Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
            limpiar();
        }else {
            Toast.makeText(this, "no Eliminado", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        baseDatos= new BaseDatos(getApplicationContext(),"usuarios.db",null,1);
        ButterKnife.bind(this);

    }
}
