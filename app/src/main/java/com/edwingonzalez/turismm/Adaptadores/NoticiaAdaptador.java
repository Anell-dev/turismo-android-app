package com.edwingonzalez.turismm.Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.edwingonzalez.turismm.*;
import com.edwingonzalez.turismm.Clases.Noticia;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
    public void onBindViewHolder(@NonNull NoticiaAdaptador.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
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

        holder.imageViewLugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(context, Detalles.class);
                    intent.putExtra("titulo", lista.get(position).getTitulo());
                    intent.putExtra("detalles", lista.get(position).getContenido());

                    Drawable drawable = lista.get(position).getImagen();
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    File tempFile = new File(context.getCacheDir(), "image.png");
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.close();
                    Uri imageUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", tempFile);
                    intent.putExtra("imageUri", imageUri.toString());

                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        holder.imageViewCompartir.setOnClickListener(v -> {
            Drawable drawable = lista.get(position).getImagen();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, lista.get(position).getTitulo(), null);
            Uri uri = Uri.parse(path);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/jpeg");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            context.startActivity(Intent.createChooser(shareIntent, "Compartir imagen usando"));
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
