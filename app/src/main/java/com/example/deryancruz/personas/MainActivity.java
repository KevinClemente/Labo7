package com.example.deryancruz.personas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deryancruz.personas.Entidades.DBHelper;

public class MainActivity extends AppCompatActivity {

    private Button  btn_registrar, btn_consultar,btn_consultarS, getBtn_consultarLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarControles();
        DBHelper.getInstance(this);


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), Activity_Registrar.class);
                startActivity(intent);
            }
        });

        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), ActivityModificar.class);
                startActivity(intent);
            }
        });

    }

    public void inicializarControles(){
        btn_registrar = findViewById(R.id.mostrar_registro);
        btn_consultar = findViewById(R.id.consultar_usuario);
    }
}
