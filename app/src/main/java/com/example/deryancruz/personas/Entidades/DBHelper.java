package com.example.deryancruz.personas.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.deryancruz.personas.Datos.Persona;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "bd_usuarios";
    public static final String TABLA_USUARIO = "Persona";
    public static final String CAMPO_ID = "dui";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+"("+CAMPO_ID+" TEXT,"+ CAMPO_NOMBRE+" TEXT)";

    public static DBHelper myDB= null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context ctx)
    {
        if(myDB == null)
        {
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context){
        super(context, DB_NAME, null,1);
        this.context = context;
        db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_NOMBRE);
        onCreate(db);
    }

    //Agregar una persona a la base de datos
    public boolean add(Persona p){
        ContentValues values = new ContentValues();
        //Agregamos los valores al content values
        values.put(CAMPO_ID,p.getDui());
        values.put(CAMPO_NOMBRE,p.getNombre());
        //ejecutamos la sentencia
        db.insert(TABLA_USUARIO, null, values);
        Toast.makeText(context, "Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    //metodo para buscar  una persona  por medio del dui
    public Persona findUser(String dui)
    {
        Persona p;
        //con el vamos a buscar
        String[] parametros  = {dui};
        String[] campos = {CAMPO_NOMBRE};

        try{
            Cursor cursor = db.query(TABLA_USUARIO, campos,CAMPO_ID+"=?", parametros,null,null,null);
            cursor.moveToFirst();
            p = new Persona(dui,cursor.getString(0));
        }catch (Exception e)
        {
            p=null;
        }

        return p;
    }

    //Metodo para editar una persona
    public boolean editUser(Persona p)
    {
        String[] parametros = {p.getDui()};
        String[] campos ={CAMPO_NOMBRE};
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOMBRE,p.getNombre());
        db.update(TABLA_USUARIO, values, CAMPO_ID+"=?", parametros);
        Toast.makeText(context, "Usuario Actualizado con exito", Toast.LENGTH_LONG).show();
        return true;
    }

    //Método para eliminar una persona
    public boolean deleteUser (String dui)
    {
        String[] parametros = {dui};
        db.delete(TABLA_USUARIO, CAMPO_ID+"=?",parametros);
        Toast.makeText(context, "Usuario Eliminado con exito", Toast.LENGTH_LONG).show();
        return true;
    }

}
