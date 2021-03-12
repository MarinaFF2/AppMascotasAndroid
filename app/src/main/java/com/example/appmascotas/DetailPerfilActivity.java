package com.example.appmascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import com.example.appmascotas.adapter.PetPerfilAdapter;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.util.ArrayList;

public class DetailPerfilActivity extends AppCompatActivity {
    private ArrayList<Pet> listPets;
    private RecyclerView rvListPets;
    private CircularImageView circularImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perfil);

        addMenu();
        addRecyclerView();
        addCircularImageView();
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
    private void addCircularImageView() {
        circularImageView = findViewById(R.id.imgFotoPerfil);
        //src
        //mostramos la imagen de la mascota
        circularImageView.setImageResource(listPets.get(0).getFoto());

        // Set Color
        circularImageView.setCircleColor(Color.WHITE);
        // or with gradient
        circularImageView.setCircleColorStart(Color.BLACK);
        circularImageView.setCircleColorEnd(Color.RED);
        circularImageView.setCircleColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

        // Set Border
        circularImageView.setBorderWidth(10f);
        circularImageView.setBorderColor(Color.BLACK);
        // or with gradient
        circularImageView.setBorderColorStart(Color.BLACK);
        circularImageView.setBorderColorEnd(Color.RED);
        circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

        // Add Shadow with default param
        circularImageView.setShadowEnable(true);
        // or with custom param
        circularImageView.setShadowRadius(7f);
        circularImageView.setShadowColor(Color.RED);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
    }

    private void addRecyclerView() {
        //recogemos la mascota
        Pet pet = (Pet) getIntent().getSerializableExtra("pet");
        listPets = Pet.ponerPerfil(pet);

        //mostramos el nombre de la mascota
        TextView tvNombrePerfil = (TextView) findViewById(R.id.tvNombrePerfil);
        tvNombrePerfil.setText(listPets.get(0).getName());

        //extendes Fragment, hay que heredarlo para que funcione
        rvListPets = (RecyclerView) findViewById(R.id.rvListPets);

        //añado layout de como se va a ver
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rvListPets.setLayoutManager(gridLayoutManager);

        //añado adaptador
        PetPerfilAdapter adapter = new PetPerfilAdapter(listPets);
        rvListPets.setAdapter(adapter);
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