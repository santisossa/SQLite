package com.santiago.sqlite.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.santiago.sqlite.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrarUsuario:
                startActivity(new Intent(this,RegistroActivity.class));
                break;
            case R.id.consultarUsuario:
                startActivity(new Intent(this,ConsultarActivity.class));
                break;
            case R.id.spinnerUsuario:
                startActivity(new Intent(this,ComboActivity.class));
                break;
            case R.id.listarUsuario:
                startActivity(new Intent(this, ListarActivity.class));
                break;
            case R.id.registrarMascota:
                startActivity(new Intent(this, RegistroMascotaActivity.class));
                break;
            case R.id.listarMascota:
                startActivity(new Intent(this, ListarMascotaActivity.class));
                break;
        }
    }
}
