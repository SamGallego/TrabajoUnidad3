package com.example.trabajounidad3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdaptadorPedido extends RecyclerView.Adapter<AdaptadorPedido.PedidoViewHolder> {

    private ArrayList<Plato> pedido;

    public AdaptadorPedido(ArrayList<Plato> pedido) {
        this.pedido = pedido;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedido, parent, false);
        return new PedidoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Plato plato = pedido.get(position);
        holder.pedidoNombre.setText(plato.getNombre());
        holder.pedidoPrecio.setText(String.format("%.2f €", (float) plato.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return pedido.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView pedidoNombre;
        TextView pedidoPrecio;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            pedidoNombre = itemView.findViewById(R.id.pedidoNombre);
            pedidoPrecio = itemView.findViewById(R.id.pedidoPrecio);
        }
    }
}
