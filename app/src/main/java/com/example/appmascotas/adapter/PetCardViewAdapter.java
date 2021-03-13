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

public class PetCardViewAdapter extends RecyclerView.Adapter<PetCardViewAdapter.PetViewHolder>{
    private Pet p;
    private Activity activity;
    private ConexionBBDD connection;

    public PetCardViewAdapter(Pet p, Activity activity) {
        connection = new ConexionBBDD(activity,"bd_pets",null,1);
        this.p = p;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enlazamos la clase con el layout
        return new PetViewHolder((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position) {
        //mostramos textos
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
                connection.updateLikePet(p.getId(),p.getLike());

                //mostramos el nuevo like
                petViewHolder.tvCvLike.setText(p.getLike()+"");
            }
        });
        //ir al pefil de la mascota
        petViewHolder.cvContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creamos el bundle para pasarle la mascota
                Intent intent = new Intent(activity, DetailPerfilActivity.class);
                intent.putExtra("pet",p);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
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
