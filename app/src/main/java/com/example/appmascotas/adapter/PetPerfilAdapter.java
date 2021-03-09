package com.example.appmascotas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appmascotas.Pet;
import com.example.appmascotas.R;

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
