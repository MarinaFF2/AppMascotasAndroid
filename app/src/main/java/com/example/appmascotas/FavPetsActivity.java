package com.example.appmascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.example.appmascotas.ConexionBBDD.ConexionBBDD;
import com.example.appmascotas.adapter.ListPetAdapter;
import java.util.ArrayList;

public class FavPetsActivity extends AppCompatActivity {
    private ArrayList<Pet> listFavPets;
    private RecyclerView rvListFavsPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_pets);

        addMenu();
        addRecyclerView();
    }

    private void addMenu() {
        //añadimos el action bar a la activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ponemos el icono de la app
        getSupportActionBar().setIcon(R.drawable.icon_huella);
        //ponemos el icono de goBack
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addRecyclerView() {
        rvListFavsPets = (RecyclerView) findViewById(R.id.rvListFavsPets);

        //añado layout de como se va a ver
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListFavsPets.setLayoutManager(linearLayoutManager);

        //buscamos las 5 mascotas favoritas
        ConexionBBDD connection = new ConexionBBDD(this,"bd_pets",null,1);
        listFavPets = connection.listaFavsPets();

        //añado adaptador
        ListPetAdapter adapter = new ListPetAdapter(listFavPets, this);
        rvListFavsPets.setAdapter(adapter);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}