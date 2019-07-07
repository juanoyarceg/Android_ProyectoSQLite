package com.example.proyectosqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;


import java.util.ArrayList;

public class NegocioMantenedorPersona extends SQLiteOpenHelper {

    private static final int VERSION_BD = 3;
    private static final String NOMBRE_BD = "test.db";
    private static final String TABLE_NAME = "persona";
    private static final String fieldObjetRut = "rut";
    private static final String fieldObjetNombre = "nombre";
    private static final String fieldObjetEdad = "edad";


    public NegocioMantenedorPersona(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + " ( " + fieldObjetRut + " TEXT PRIMARY KEY, "
                + fieldObjetNombre + " TEXT , " + fieldObjetEdad + " INTEGER )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL);
        onCreate(db);
    }

    //Métodos customize
    public int insertarDatos(Persona persona){
        SQLiteDatabase db = getWritableDatabase();
        try
        {
            String sql = "INSERT INTO PERSONA (rut,nombre,edad) VALUES (?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1,persona.getRut());
            statement.bindString(2,persona.getNombre());
            statement.bindLong(3,persona.getEdad());
            return (int)statement.executeInsert();
        }
        catch(SQLException ex)
        {
            return 0;
        }
        finally {
            db.close();
        }
    }

    public ArrayList<Persona> listarPersonas(){
        SQLiteDatabase db = this.getReadableDatabase();
        try
        {
            ArrayList<Persona> personas = new ArrayList();
            String sql = "SELECT * FROM persona ORDER BY rut";
            Cursor cursor = db.rawQuery(sql,null);
            while(cursor.moveToNext())
            {
                Persona p = new Persona();
                p.setRut(cursor.getString(0));
                p.setNombre(cursor.getString(1));
                p.setEdad(cursor.getInt(2));
                personas.add(p);
            }
            cursor.close();
            return personas;

        }
        catch(SQLException ex)
        {
            return null;
        }
        finally {
            db.close();
        }

    }
}
