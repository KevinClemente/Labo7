package com.example.deryancruz.personas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.deryancruz.personas.Datos.Persona;
import com.example.deryancruz.personas.Entidades.DBHelper;

public class Activity_Registrar extends AppCompatActivity {
    private EditText txtId, txtNombre;
    private Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        inicializarControles();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = DBHelper.myDB.add(new Persona(
                        txtId.getText().toString(),
                        txtNombre.getText().toString()
                ));
                if(flag)
                {
                    Log.d("Edit", txtNombre.getText().toString());
                }
            }
        });


    }

    public void inicializarControles(){
        btnEnviar = findViewById(R.id.btnRegistrar);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
    }
}
