package com.example.deryancruz.personas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deryancruz.personas.Datos.Persona;
import com.example.deryancruz.personas.Entidades.DBHelper;

public class ActivityModificar extends AppCompatActivity {
    private EditText id, nombre;
    private Button btnBuscar, btnEliminar, btnActualizar, btnLimpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        inicializarControles();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona p = DBHelper.myDB.findUser(id.getText().toString());
                if(p == null)
                {
                    Toast.makeText(getApplicationContext(), "El usuario no fue encontrado", Toast.LENGTH_LONG).show();
                    Limpiar();
                }
                else{
                    nombre.setText(p.getNombre());
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper.myDB.editUser(new Persona(id.getText().toString(), nombre.getText().toString()));
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper.myDB.deleteUser(id.getText().toString());
                Limpiar();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Limpiar();
            }
        });
    }

    public void inicializarControles(){
        id = findViewById(R.id.txtIdM);
        nombre = findViewById(R.id.txtNombreM);
        btnBuscar = findViewById(R.id.btnBuscarM);
        btnEliminar = findViewById(R.id.btnEliminarM);
        btnActualizar = findViewById(R.id.btnActualizarM);
        btnLimpiar = findViewById(R.id.btnLimpiarM);
    }

    public void Limpiar(){
        nombre.setText("");
        id.setText("");
    }
}
