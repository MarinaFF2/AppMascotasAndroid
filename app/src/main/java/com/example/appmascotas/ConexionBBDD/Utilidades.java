package com.example.appmascotas.ConexionBBDD;

public class Utilidades {
    //Nombre de la BD
    public static final String NAMEBBDD="bd_pets";
    //Version de la BD
    public static final int DB_VERSION = 1;

    //Constantes campos tabla pet
    public static final String TABLA_PET="pet";
    public static final String CAMPO_ID_PET="id_pet";
    public static final String CAMPO_NOMBRE_PET="name_pet";
    public static final String CAMPO_FOTO_PET="foto_pet";
    public static final String CAMPO_LIKES_PET="likes_pet";

    public static final String CREAR_TABLA_PET = "CREATE TABLE " +
            ""+TABLA_PET+" ("+CAMPO_ID_PET+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_PET+" TEXT,"+CAMPO_LIKES_PET+" INTEGER,"+CAMPO_FOTO_PET+" INTEGER)";
}
