package com.example.appmascotas.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmascotas.ConexionBBDD.ConexionBBDD;
import com.example.appmascotas.Pet;
import com.example.appmascotas.adapter.PetAdapter;
import com.example.appmascotas.R;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    private ArrayList<Pet> listPets;
    private RecyclerView rvListPets;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        addRecyclerView();
        return view;
    }

    private void addRecyclerView() {
        //extendes Fragment, hay que heredarlo para que funcione
        rvListPets = (RecyclerView) view.findViewById(R.id.rvListPets);

        //añado layout de como se va a ver
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListPets.setLayoutManager(linearLayoutManager);

        //buscamos las mascotas
        ConexionBBDD connection = new ConexionBBDD(view.getContext(),"bd_pets",null,1);
        listPets = connection.listaPets();

        //añado adaptador
        PetAdapter adapter = new PetAdapter(listPets, view.getContext());
        rvListPets.setAdapter(adapter);

    }
}
