package com.example.appmascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.appmascotas.ConexionBBDD.ConexionBBDD;
import com.example.appmascotas.fragment.ViewPagerFragment;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    //declaramos la bbdd
    private ConexionBBDD connection;
    private ArrayList<Pet> listaPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new ConexionBBDD(this);
        if(connection.listaPets() == null) {
            // rellenamos el array e insertamos las mascotas en la bbdd la 1º vez
            Pet.insertarArrayPetBBDD(this);
        }

        addMenu();
        addViewPager();
    }

    private void addViewPager() {
        //conseguimos la lista
        listaPets = connection.listaPets();
        //añadimos adaptador view pager
        ScreenSlidePageFragment  pagerAdapter = new ScreenSlidePageFragment (getSupportFragmentManager());
        for(int i = 0; i<listaPets.size()-1;i++) {
            Fragment fragment = ViewPagerFragment.newInstance(listaPets.get(i),i);
            pagerAdapter.addFragment(fragment);
        }
        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        //pager.setPageTransformer(true, new ZoomOutPageTransformer(listaPets,MainActivity.this));
    }

    private void addMenu() {
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
        //añadimos el menu
        getMenuInflater().inflate(R.menu.menu_back, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        //metodo para saber la opcion seleccionada de los items del menu
        switch (item.getItemId()){
            case R.id.action_fav_5: //si pulsamos en el fav 5
                Intent intent1 = new Intent(this, FavPetsActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_contact: //si pulsamos en el contacto
                Intent intent2 = new Intent(this, ContactActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_acercaDe: //si pulsamos en el acerca de
                Intent intent3 = new Intent(this, BioActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onClickDone(View view) {
        //este es el boton de la imagen camara
        CharSequence text = "A screenshot has been made";
        int duration = Snackbar.LENGTH_SHORT;

        Snackbar snackbar = Snackbar.make(findViewById(R.id.content),text,duration);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Undo!", Toast.LENGTH_SHORT).show();
            }
        });
        //mostramos snackbar
        snackbar.show();
    }

    public class ScreenSlidePageFragment  extends FragmentPagerAdapter {
        List<Fragment> fragments;
        public ScreenSlidePageFragment (@NonNull FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<Fragment>();
        }
        public void addFragment(Fragment fragment) {
            //añadimos un fragment a la lista
            this.fragments.add(fragment);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            //devolvemos el fragment de la posicion
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            //devolvemos el tamaño de la lista
            return listaPets.size()-1;
        }
    }
}