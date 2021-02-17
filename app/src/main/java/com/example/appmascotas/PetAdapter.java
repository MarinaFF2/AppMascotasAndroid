package com.example.appmascotas;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{
    ArrayList<Pet> listPets;
    Activity activity;
    public PetAdapter(ArrayList<Pet> listPets, Activity activity) {
        this.listPets = listPets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enlazamos la clase con el layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position) {
        //mostramos textos
        Pet p = listPets.get(position);
        petViewHolder.imgCvFoto.setImageResource(p.getFoto());
        petViewHolder.tvCvNombre.setText(p.getName());
        petViewHolder.tvCvLike.setText(p.getLike());

        //añado listener dar like
        petViewHolder.imgCVHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"has dado like a "+p.getName(),Toast.LENGTH_SHORT).show();
                //añadimos el like
                int like = Integer.parseInt(p.getLike());
                p.setLike((like+1)+"");
                //actualizamos la lista
                listPets.get(position).setLike(p.getLike());
                petViewHolder.tvCvLike.setText(p.getLike());
            }
        });
    }

    public ArrayList<Pet> getListPets() {
        return listPets;
    }

    @Override
    public int getItemCount() {
        return listPets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCvFoto;
        private ImageView imgCVHeart;
        private TextView tvCvNombre;
        private TextView tvCvLike;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCvFoto = (ImageView) itemView.findViewById(R.id.imgCvFoto);
            imgCVHeart = (ImageView) itemView.findViewById(R.id.imgCVHeart);
            tvCvNombre = (TextView) itemView.findViewById(R.id.tvCvNombre);
            tvCvLike = (TextView) itemView.findViewById(R.id.tvCvLike);
        }
    }
}
