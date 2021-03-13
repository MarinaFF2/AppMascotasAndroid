package com.example.appmascotas;

import android.app.Activity;
import com.example.appmascotas.ConexionBBDD.ConexionBBDD;
import java.io.Serializable;
import java.util.ArrayList;

public class Pet implements Serializable {
    private int id;
    private String name;
    private int Foto;
    private int like;

    public Pet() {
    }

    public Pet(int id, String name, int foto, int like) {
        this.id = id;
        this.name = name;
        Foto = foto;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFoto() {
        return Foto;
    }
    public void setFoto(int foto) {
        Foto = foto;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public static ArrayList<Pet> initListPets() {
        ArrayList<Pet> listPets = new ArrayList<> ();
        listPets.add(new Pet(0,"Bridget",R.drawable.pet_bridget,10));
        listPets.add(new Pet(0,"Chole",R.drawable.pet_chloe,10));
        listPets.add(new Pet(0,"Daisy",R.drawable.pet_daisy,10));
        listPets.add(new Pet(0,"Max",R.drawable.pet_max,30));
        listPets.add(new Pet(0,"Mel",R.drawable.pet_mel,47));
        listPets.add(new Pet(0,"Norman",R.drawable.pet_norman,3));
        listPets.add(new Pet(0,"Ozono",R.drawable.pet_ozono,4));
        listPets.add(new Pet(0,"Penaut",R.drawable.pet_peanut,9));
        listPets.add(new Pet(0,"Plumita",R.drawable.pet_plumita,5));
        listPets.add(new Pet(0,"Pompon",R.drawable.pet_pompon,8));
        listPets.add(new Pet(0,"Tatto",R.drawable.pet_tatoo,40));
        return listPets;
    }
    public static void insertarArrayPetBBDD(Activity activity){
        ConexionBBDD connection = new ConexionBBDD(activity,"bd_pets",null,1);
        ArrayList<Pet> listPets = initListPets();
        for (Pet pet : listPets) {
            //insertamos las mascotas del array
            connection.insertPet(pet, activity);
        }
    }
    public static ArrayList<Pet> ponerPerfil(Pet pet){
        //lista de del perfil
        ArrayList<Pet> listPerfilPets = new ArrayList<>();

        //contruimos datos random de esa mascota
        listPerfilPets.add(new Pet(pet.getId(),pet.getName(),pet.getFoto(),12));
        listPerfilPets.add(new Pet(pet.getId(),pet.getName(),pet.getFoto(),22));
        listPerfilPets.add(new Pet(pet.getId(),pet.getName(),pet.getFoto(),0));
        listPerfilPets.add(new Pet(pet.getId(),pet.getName(),pet.getFoto(),17));
        listPerfilPets.add(new Pet(pet.getId(),pet.getName(),pet.getFoto(),283));
        listPerfilPets.add(new Pet(pet.getId(),pet.getName(),pet.getFoto(),83));

        return listPerfilPets;
    }
}
