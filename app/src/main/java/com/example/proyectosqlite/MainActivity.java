package com.example.proyectosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GuardarRegistro(View view) {
        try
        {
            EditText auxRut = (EditText) findViewById(R.id.txtRut);
            EditText auxNombre = (EditText) findViewById(R.id.txtNombre);
            EditText auxEdad = (EditText) findViewById(R.id.txtEdad);

            String rut = auxRut.getText().toString();
            String nombre = auxNombre.getText().toString();
            int edad = Integer.parseInt(auxEdad.getText().toString());

            Persona p = new Persona();
            p.setRut(rut);
            p.setNombre(nombre);
            p.setEdad(edad);

            NegocioMantenedorPersona mantenedor = new NegocioMantenedorPersona(this);
            if(mantenedor.insertarDatos(p) >= 1)
            {
                Mensaje("Registro exitoso");
                auxRut.setText("");
                auxNombre.setText("");
                auxEdad.setText("");
                auxRut.requestFocus();
            }
            else
            {
                Mensaje("Problemas al momento de registrar");
            }
        }
        catch(Exception ex)
        {
            Mensaje(ex.getMessage());
        }

    }

    public void Mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void MostrarListadoPersonas(View view) {
        Intent intent = new Intent(this,ListadoPersonas.class);
        startActivity(intent);
    }
}
