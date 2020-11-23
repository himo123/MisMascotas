package com.himo.mismascotas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

ArrayList<Mascota> mascotas;
Activity contexto;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity contexto){
        this.mascotas = mascotas;
        this.contexto = contexto;
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private ImageButton btnLike;
        private TextView tvNombreCV;
        private TextView tvRatingCV;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto       = (ImageView) itemView.findViewById(R.id.imgFoto);
            btnLike       = (ImageButton) itemView.findViewById(R.id.btnLike);
            tvNombreCV    = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvRatingCV    = (TextView) itemView.findViewById(R.id.tvRatingCV);
        }
    }

    @NonNull  @Override  //Inflar el layout y los pasa al ViewHolder para que obtenga los views
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflar = LayoutInflater.from(parent.getContext());
        View elemento = inflar.inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(elemento);
    }

    @Override  //asocia cada elemento de la lista con cada view
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombreCV.setText(mascota.getNombre());
        holder.tvRatingCV.setText(String.valueOf(mascota.getRating()));

//        Mostrar la información de una mascota
//        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(contexto, "Mascota: " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
//                Intent intento = new Intent(activity, DetalleContacto.class);
//                intento.putExtra(activity.getResources().getString(R.string.pnombre), contacto.getNombre());
//                intento.putExtra(activity.getResources().getString(R.string.ptelefono), contacto.getTelefono());
//                intento.putExtra(activity.getResources().getString(R.string.pemail), contacto.getEmail());
//                activity.startActivity(intento);
//            }
//        });

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.btnLike.getVisibility() == View.VISIBLE) {
                    mascota.setRating(mascota.getRating() + 1);
                    holder.tvRatingCV.setText(String.valueOf(mascota.getRating()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {  //Cantidad de elementos que contiene la lista
        return mascotas.size();
    }

}
