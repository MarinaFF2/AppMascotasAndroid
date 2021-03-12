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
import com.example.appmascotas.Pet;
import com.example.appmascotas.R;
import com.example.appmascotas.adapter.PetCardViewAdapter;

public class ViewPagerFragment extends Fragment {
    private static final String INDEX = "index";
    private Pet p;

    public static ViewPagerFragment newInstance(Pet pet, int index) {
        // Crear una instancia de un nuevo fragmento
        ViewPagerFragment fragment = new ViewPagerFragment();

        // Guarda los parámetros
        Bundle bundle = new Bundle();
        bundle.putSerializable("pet",pet);

        //metemos el argumento
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        // Cargar parámetros cuando se realiza la creación inicial del fragmento
        if(getArguments() != null){
            //conseguimos los bundles
            this.p = (Pet) getArguments().getSerializable("pet");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_view_pager,container,false);
        addRecycler(viewGroup);
        return viewGroup;
    }
    private void addRecycler(ViewGroup viewGroup) {
        RecyclerView rvListFavsPets = (RecyclerView) viewGroup.findViewById(R.id.rvListPets);

        //añado layout de como se va a ver
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvListFavsPets.setLayoutManager(linearLayoutManager);

        //añado adaptador
        PetCardViewAdapter adapter = new PetCardViewAdapter(p, getActivity());
        rvListFavsPets.setAdapter(adapter);
    }

}
