package com.example.appmascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pet> listPets;
    private RecyclerView rvListPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null) {
            //recogemos las sesiones
            listPets = (ArrayList<Pet>) savedInstanceState.getSerializable("listPets");
        }else if(savedInstanceState==null) {
            //instancaimos el arrayList
            initListPets();
        }
        aniadimosMenu();
        recyclerView();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //guardadmos las sesiones
        outState.putSerializable("listPets", listPets);
        super.onSaveInstanceState(outState);
    }
    private void initListPets() {
        listPets = new ArrayList<> ();
        listPets.add(new Pet("Bridget",R.drawable.pet_bridget,"0"));
        listPets.add(new Pet("Chole",R.drawable.pet_chloe,"0"));
        listPets.add(new Pet("Daisy",R.drawable.pet_daisy,"0"));
        listPets.add(new Pet("Max",R.drawable.pet_max,"0"));
        listPets.add(new Pet("Mel",R.drawable.pet_mel,"0"));
        listPets.add(new Pet("Norman",R.drawable.pet_norman,"0"));
        listPets.add(new Pet("Ozono",R.drawable.pet_ozono,"0"));
        listPets.add(new Pet("Penaut",R.drawable.pet_peanut,"0"));
        listPets.add(new Pet("Plumita",R.drawable.pet_plumita,"0"));
        listPets.add(new Pet("Pompon",R.drawable.pet_pompon,"0"));
        listPets.add(new Pet("Tatto",R.drawable.pet_tatoo,"0"));
    }

    private void aniadimosMenu() {
        //añadimos el action bar a la activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ponemos el icono de la app
        getSupportActionBar().setIcon(R.drawable.icon_huella);

        //añadimos la accion a los items del menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        //metodo para crear el menu y sus items
        //añadimos el menu el add
        getMenuInflater().inflate(R.menu.menu_fav, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        //metodo para saber la opcion seleccionada de los items del menu
        switch (item.getItemId()){
            case R.id.action_create_order: //si pulsamos en el +
                Intent i = new Intent(this, FavPetsActivity.class);
                ArrayList<Pet> listFavPets = randomFavPets();
                i.putExtra("listFavPets", listFavPets);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private ArrayList<Pet> randomFavPets(){
        ArrayList<Pet> listFavPets = new ArrayList<> ();
        final int min = 0;
        final int max = listPets.size()-1;
        int cont = 0;
        while(cont <5) {//mientras sea menor que 5
            int random = new Random().nextInt((max - min) + 1) + min;
            if (listFavPets.size() != 0) {
                for (Pet p : listFavPets) {
                    if (!listPets.get(random).getName().equals(p.getName())) {
                        cont++;
                        listFavPets.add(listPets.get(random));
                        break;
                    }
                }
            }else{
                cont++;
                listFavPets.add(listPets.get(random));
            }
        }
        return  listFavPets;
    }
    private void recyclerView() {
        rvListPets = (RecyclerView) findViewById(R.id.rvListPets);
        //añado layout de como se va a ver
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListPets.setLayoutManager(linearLayoutManager);
        //añado adaptador
        PetAdapter adapter = new PetAdapter(listPets, this);
        listPets = adapter.getListPets();
        rvListPets.setAdapter(adapter);
    }
}