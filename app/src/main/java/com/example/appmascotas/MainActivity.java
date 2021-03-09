package com.example.appmascotas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.appmascotas.ConexionBBDD.ConexionBBDD;
import com.example.appmascotas.fragment.PerfilFragment;
import com.example.appmascotas.fragment.RecyclerViewFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    //declaramos la bbdd
    private ConexionBBDD connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new ConexionBBDD(this,"bd_pets",null,1);
        if(connection.listaPets() == null) {
            // rellenamos el array e insertamos las mascotas en la bbdd la 1º vez
            Pet.insertarArrayPetBBDD(this);
        }

        addMenu();
        addViewPager();
        addTabLayout();
    }

    private void addViewPager() {
        //añadimos adaptador view pager
        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
    }

    private void addTabLayout() {
        //añadimos el tabLayoutr a la activity
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_home));
        //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_dog));
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
    }

    private class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(@NonNull FragmentManager fm){
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //el fragment que va a mostrar
            switch (position){
                case 0:
                    return new RecyclerViewFragment();
                case 1:
                    return new PerfilFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            //numero de paginas que va a mostrar
            return 2;
        }
        @Nullable
        @Override
       public CharSequence getPageTitle(int position) {
            //para cuando hagamos click en los titulos
            switch (position){
                case 0:
                    return  "Home";
                   // return  getResources().getDrawable(R.drawable.icon_home);
                case 1:
                    return "Dog";
                    //return getResources().getDrawable(R.drawable.icon_dog);
            }
            return null;
        }
    }
}