package com.example.appmascotas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmascotas.ConexionBBDD.ConexionBBDD;
import com.example.appmascotas.Pet;
import com.example.appmascotas.R;
import com.example.appmascotas.fragment.PerfilFragment;

import java.util.ArrayList;

public class PetPerfilAdapter extends RecyclerView.Adapter<PetPerfilAdapter.PetViewHolder>{
    private ArrayList<Pet> listPets;

    public PetPerfilAdapter(ArrayList<Pet> listPets) {
        this.listPets = listPets;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enlazamos la clase con el layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil,parent,false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position) {
        //mostramos textos
        Pet p = listPets.get(position);
        petViewHolder.imgCvFoto.setImageResource(p.getFoto());
        petViewHolder.tvCvLike.setText(p.getLike()+"");
        /*
        petViewHolder.cvContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new PerfilFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_PerfilFragment, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });
         */
    }

    @Override
    public int getItemCount() {
        return listPets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCvFoto;
        private TextView tvCvLike;
        private CardView cvContacto;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            cvContacto = (CardView) itemView.findViewById(R.id.cvContacto);
            imgCvFoto = (ImageView) itemView.findViewById(R.id.imgCvFoto);
            tvCvLike = (TextView) itemView.findViewById(R.id.tvCvLike);
        }
    }
}
