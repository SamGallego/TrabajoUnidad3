package com.example.trabajounidad3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdaptadorPlato extends RecyclerView.Adapter<AdaptadorPlato.PlatoViewHolder> {

    private ArrayList<Plato> coleccion;
    private OnItemClickListener onItemClickListener;

    public AdaptadorPlato(ArrayList<Plato> coleccion) {
        this.coleccion = coleccion;
    }

    public interface OnItemClickListener {
        void onItemClick(Plato plato);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.plato, parent, false);
        return new PlatoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
        Plato plato = coleccion.get(position);
        holder.imageView.setImageResource(plato.getFoto());
        holder.nombre.setText(plato.getNombre());
        holder.descripcion.setText(plato.getDescripcion());
        holder.alergenos.setText(plato.getAlergenos());
        // En AdaptadorPlato.java, dentro del método onBindViewHolder
        holder.precio.setText(String.format("%.2f €", plato.getPrecio()));


        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(plato);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coleccion.size();
    }

    public static class PlatoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nombre;
        TextView descripcion;
        TextView alergenos;
        TextView precio;

        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nombre = itemView.findViewById(R.id.nombre);
            descripcion = itemView.findViewById(R.id.descripcion);
            alergenos = itemView.findViewById(R.id.alergenos);
            precio = itemView.findViewById(R.id.precio);
        }
    }
}

