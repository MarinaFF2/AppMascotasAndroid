package com.example.appmascotas.ConexionBBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appmascotas.Pet;

import java.util.ArrayList;

public class ConexionBBDD extends SQLiteOpenHelper {
    //declaro la bariable para poder interactuar con la bbdd
    private SQLiteDatabase db;
    private Context context;

    //al llamar al constructor se crea la bbdd
    public ConexionBBDD(@Nullable Context context, @Nullable String nameBDD, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameBDD, factory, version);
        this.context = context;
    }

    /**
     * Metodo encargado de generar las tablas correspondientes a las clases en entidades
     * Se llama cuando se crea la base de datos por primera vez.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //creamos tabla de usuario
        db.execSQL(Utilidades.CREAR_TABLA_PET);//Ejecuta una sola sentencia SQL que no es una sentencia SELECT o cualquier otra sentencia SQL que devuelva datos.
    }

    /**
     * MEtodo para comprobar si ya se ha creado la bbdd
     * Se llama cuando la base de datos necesita ser actualizada.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //eliminamos la tabla usuarios si ya existe
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }

    public void insertPet(Pet pet) {
        db = getWritableDatabase();//declaro la bariable para poder interactuar con la bbdd

        /*forma 1
             Método de conveniencia para insertar una fila en la base de datos.
            insert(String table, String nullColumnHack, ContentValues values)
            devuelve Long
         */
        //metemos los valores a insertar
        ContentValues values = new ContentValues();
        //values.put(Utilidades.CAMPO_ID_PET,pet.getId());
        values.put(Utilidades.CAMPO_NOMBRE_PET,pet.getName());
        values.put(Utilidades.CAMPO_FOTO_PET,pet.getFoto());
        values.put(Utilidades.CAMPO_LIKES_PET,pet.getLike());

        //insertamos el usuario
        //devuelve un numero Long dependiento del resultado del insert
        //si idResult es igual 1 ha tenido exito
        Long result = db.insert(Utilidades.TABLA_PET,Utilidades.CAMPO_ID_PET,values);
        if(result > 0) {
            Toast.makeText(context, "Inserted the pet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Error inserted the pet", Toast.LENGTH_SHORT).show();
        }

        //forma 2
        /*
        String sql = "INSERT INTO "+Utilidades.TABLA_PET+
                " ("+Utilidades.CAMPO_ID_PET+", "+Utilidades.CAMPO_NOMBRE_PET+", "+Utilidades.CAMPO_FOTO_PET+", "+Utilidades.CAMPO_LIKES_PET+") " +
                "VALUES ("+pet.getId()+", '"+pet.getName()+"', '"+pet.getFoto()+"', '"+pet.getLike()+"');" ;
        db.execSQL(sql);
         */

        db.close();
    }

    public void deletePet(int id){
        db = getWritableDatabase(); //Crear y / o abrir una base de datos que se utilizará para lectura y escritura.
        /*
            Método de conveniencia para insertar una fila en la base de datos.
            delete(String table, String whereClause, String[] whereArgs)
            devuelve int
         */
        String[] parametros={id+""};

        int result = db.delete(Utilidades.TABLA_PET,Utilidades.CAMPO_ID_PET+"=?",parametros);
        if(result > 0) {
            Toast.makeText(context, "Delete the pet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Error deleted the pet", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public void updateLikePet(int id, int like) {
        db = getWritableDatabase(); //Crear y / o abrir una base de datos que se utilizará para lectura y escritura.

         /*
            Forma 1
            Método de conveniencia para la actualización de las filas de la base de datos.
            update(String table, ContentValues values, String whereClause, String[] whereArgs)
            devuleve un int
         */
        String[] parametros={id+""};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_LIKES_PET,like);

        int result = db.update(Utilidades.TABLA_PET,values,Utilidades.CAMPO_ID_PET+"=?",parametros);
        if(result > 0) {
            Toast.makeText(context, "Updated the pet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Error updated the pet", Toast.LENGTH_SHORT).show();
        }

        //forma 2
            /*
        String sql = "UPDATE "+Utilidades.TABLA_PET+
                " SET "+Utilidades.CAMPO_LIKES_PET+" = "+like+
                " WHERE "+Utilidades.CAMPO_ID_PET+" = "+id+";";
        db.execSQL(sql);
             */


        db.close();
    }
    public Pet buscarPetFirst(int id){
        db = getReadableDatabase();
        Pet pet = null;
        String[] parametros={id+""};

        //forma 1
        String[] campos={Utilidades.CAMPO_ID_PET,Utilidades.CAMPO_ID_PET,Utilidades.CAMPO_FOTO_PET,Utilidades.CAMPO_LIKES_PET};
        try {
            Cursor cursor =db.query(Utilidades.TABLA_PET,campos,Utilidades.CAMPO_ID_PET+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            pet.setId(cursor.getInt(0));
            pet.setName(cursor.getString(1));
            pet.setLike(cursor.getInt(2));
            pet.setFoto(cursor.getInt(3));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(context,"Not existed the pet",Toast.LENGTH_SHORT).show();
        }

            //forma 2
        /*
            try {
                //select nombre,telefono from usuario where codigo=?
                Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_ID_PET+","+Utilidades.CAMPO_ID_PET+","+Utilidades.CAMPO_FOTO_PET+","+Utilidades.CAMPO_LIKES_PET+
                        " FROM "+Utilidades.TABLA_PET+" WHERE "+Utilidades.CAMPO_ID_PET+"=? ",parametros);
                cursor.moveToFirst();
                pet.setId(cursor.getInt(0));
                pet.setName(cursor.getString(1));
                pet.setLike(cursor.getInt(2));
                pet.setFoto(cursor.getInt(3));
                cursor.close();
            }catch (Exception e){
                Toast.makeText(context,"Not existed the pet",Toast.LENGTH_SHORT).show();
            }
         */
        db.close();
        return pet;
    }
    public ArrayList<Pet> listaPets(){
        //SACA TODAS LAS MASCOTAS
        ArrayList<Pet> listPets = null;

        //Crear y / o abrir una base de datos.
        db = getReadableDatabase();

        Pet pet = null;
        listPets = new ArrayList<Pet>();

        //Consulta la tabla dada, devolviendo un cursor sobre el conjunto de resultados.
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_PET,null);

        while (cursor.moveToNext()){
            pet=new Pet();
            pet.setId(cursor.getInt(0));
            pet.setName(cursor.getString(1));
            pet.setLike(cursor.getInt(2));
            pet.setFoto(cursor.getInt(3));

            listPets.add(pet);
        }
        return listPets;
    }
    public ArrayList<Pet> listaFavsPets(){
        //SACA LAS 5 MASCOTAS CON MAS LIKES
        ArrayList<Pet> listPets = null;
        db = getReadableDatabase();

        Pet pet = null;
        listPets = new ArrayList<Pet>();
        String sql ="SELECT DISTINCT * FROM "+Utilidades.TABLA_PET+" " +
                "WHERE "+Utilidades.CAMPO_LIKES_PET +"<= " +
                "(SELECT MAX("+Utilidades.CAMPO_LIKES_PET +") FROM "+Utilidades.TABLA_PET+")"+" " +
                "ORDER BY "+Utilidades.CAMPO_LIKES_PET+" DESC  LIMIT 5";
        Cursor cursor=db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            pet=new Pet();
            pet.setId(cursor.getInt(0));
            pet.setName(cursor.getString(1));
            pet.setLike(cursor.getInt(2));
            pet.setFoto(cursor.getInt(3));

            listPets.add(pet);
        }
        return listPets;
    }
}
