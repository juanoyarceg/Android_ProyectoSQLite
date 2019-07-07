package com.example.proyectosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoPersonas extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_personas);
        ListarPersonas();
    }

    private void ListarPersonas(){
        try
        {
            ListView auxLista = (ListView) findViewById(R.id.lstListadoPersonas);
            Intent intent = getIntent();
            NegocioMantenedorPersona mantenedor = new NegocioMantenedorPersona(this);
            ArrayList<Persona> listadoPersonas = mantenedor.listarPersonas();
            ArrayList<String> Personas = new ArrayList();
            for (Persona p : listadoPersonas)
            {
                Personas.add(p.getRut() + " | " + p.getNombre() + " | " + p.getEdad());
            }
            auxLista.setAdapter(new ArrayAdapter<String>
                    (this,android.R.layout.simple_list_item_1,Personas));

        }
        catch(Exception ex)
        {
            Mensaje(ex.getMessage());
        }

    }

    public void Mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}
