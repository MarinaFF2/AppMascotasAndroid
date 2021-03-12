package com.example.appmascotas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appmascotas.ConexionBBDD.ConexionBBDD;
import com.example.appmascotas.DetailPerfilActivity;
import com.example.appmascotas.Pet;
import com.example.appmascotas.R;
import java.util.ArrayList;

public class ListPetAdapter extends RecyclerView.Adapter<ListPetAdapter.PetViewHolder>{
    private ArrayList<Pet> listPets;
    private Activity activity;
    private Pet p;

    public ListPetAdapter(ArrayList<Pet> listPets, Activity activity) {
        this.listPets = listPets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enlazamos la clase con el layout
        return new PetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position) {
        //mostramos textos
        p = listPets.get(position);
        petViewHolder.imgCvFoto.setImageResource(p.getFoto());
        petViewHolder.tvCvNombre.setText(p.getName());
        petViewHolder.tvCvLike.setText(p.getLike()+"");

        //añado listener dar like
        petViewHolder.imgCVHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Has dado like a "+p.getName(),Toast.LENGTH_SHORT).show();
                //añadimos el like
                p.setLike(p.getLike()+1);
                //actualizamos la bbdd
                ConexionBBDD connection = new ConexionBBDD(activity);
                connection.updateLikePet(p.getId(),p.getLike());

                //mostramos el nuevo like
                petViewHolder.tvCvLike.setText(p.getLike()+"");
            }
        });
        //ir al pefil de la mascota
        petViewHolder.cvContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = listPets.get(position);
                //creamos el bundle para pasarle la mascota
                Intent intent = new Intent(activity, DetailPerfilActivity.class);
                intent.putExtra("pet",p);
                activity.startActivity(intent);
            }
        });
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
        private CardView cvContacto;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            cvContacto = itemView.findViewById(R.id.cvContacto);
            imgCvFoto = (ImageView) itemView.findViewById(R.id.imgCvFoto);
            imgCVHeart = (ImageView) itemView.findViewById(R.id.imgCVHeart);
            tvCvNombre = (TextView) itemView.findViewById(R.id.tvCvNombre);
            tvCvLike = (TextView) itemView.findViewById(R.id.tvCvLike);
        }
    }
}
