package com.example.appmascotas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import java.util.ArrayList;

public class FavPetsActivity extends AppCompatActivity {
    private ArrayList<Pet> listFavPets;
    private RecyclerView rvListFavsPets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_pets);

        aniadimosMenu();
        recyclerView();
    }
    private void aniadimosMenu() {
        //añadimos el action bar a la activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ponemos el icono de la app
        getSupportActionBar().setIcon(R.drawable.icon_huella);
        //ponemos el icono de goBack
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void recyclerView() {
        listFavPets = (ArrayList<Pet>) getIntent().getSerializableExtra("listFavPets");
        rvListFavsPets = (RecyclerView) findViewById(R.id.rvListFavsPets);
        //añado layout de como se va a ver
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListFavsPets.setLayoutManager(linearLayoutManager);
        //añado adaptador
        PetAdapter adapter = new PetAdapter(listFavPets, this);
        listFavPets = adapter.getListPets();
        rvListFavsPets.setAdapter(adapter);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(FavPetsActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}