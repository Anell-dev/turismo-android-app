package com.edwingonzalez.turismm.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.edwingonzalez.turismm.*;
import com.edwingonzalez.turismm.Clases.Noticia;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticiaAdaptador extends RecyclerView.Adapter<NoticiaAdaptador.ViewHolder> {
    Context context;
    ArrayList<Noticia> lista;

    public NoticiaAdaptador(Context context, ArrayList<Noticia> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public NoticiaAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.noticia_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaAdaptador.ViewHolder holder, int position) {
        holder.textViewTitulo.setText(lista.get(position).getTitulo());
        holder.textViewAutor.setText(lista.get(position).getAutor());
        holder.textViewLikes.setText(String.valueOf(lista.get(position).getLikes()));
        holder.imageViewLugar.setImageDrawable(lista.get(position).getImagen());
        holder.imageViewUser.setImageDrawable(lista.get(position).getImagenAutor());

        holder.imageViewLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidadLikes = Integer.parseInt(holder.textViewLikes.getText().toString());
                cantidadLikes++;
                holder.textViewLikes.setText(String.valueOf(cantidadLikes));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitulo, textViewAutor, textViewLikes;
        ImageView imageViewLugar, imageViewUser, imageViewLikes, imageViewCompartir;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewAutor = itemView.findViewById(R.id.textViewAutor);
            textViewLikes = itemView.findViewById(R.id.textViewLikes);

            imageViewLugar = itemView.findViewById(R.id.imageViewLugar);
            imageViewUser = itemView.findViewById(R.id.imageViewUser);
            imageViewLikes = itemView.findViewById(R.id.imageViewLikes);
            imageViewCompartir = itemView.findViewById(R.id.imageViewCompartir);
        }
    }
}
