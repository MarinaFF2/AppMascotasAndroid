package com.example.appmascotas.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmascotas.ConexionBBDD.ConexionBBDD;
import com.example.appmascotas.Pet;
import com.example.appmascotas.adapter.PetAdapter;
import com.example.appmascotas.adapter.PetPerfilAdapter;
import com.example.appmascotas.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {
    private ArrayList<Pet> listPets;
    private RecyclerView rvListPets;
    private View view;
    private CircularImageView circularImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil,container,false);
    //buscamos las mascotas
        ConexionBBDD connection = new ConexionBBDD(view.getContext(),"bd_pets",null,1);
        listPets = connection.listaPets();
        listPets = Pet.perfilPets(listPets);

        TextView tvNombrePerfil = (TextView) view.findViewById(R.id.tvNombrePerfil);
        tvNombrePerfil.setText(listPets.get(0).getName());

        addRecyclerView();
        addCircularImageView();
        return view;
    }

    private void addCircularImageView() {
        circularImageView = view.findViewById(R.id.imgFotoPerfil);
        //src
        //circularImageView.setImageResource(R.drawable.pet_daisy);
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
        //extendes Fragment, hay que heredarlo para que funcione
        rvListPets = (RecyclerView) view.findViewById(R.id.rvListPets);

        //añado layout de como se va a ver
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rvListPets.setLayoutManager(gridLayoutManager);

        //añado adaptador
        PetPerfilAdapter adapter = new PetPerfilAdapter(listPets);
        rvListPets.setAdapter(adapter);
    }

}
