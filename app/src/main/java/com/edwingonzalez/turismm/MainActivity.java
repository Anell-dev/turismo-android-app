package com.edwingonzalez.turismm;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edwingonzalez.turismm.Adaptadores.NoticiaAdaptador;
import com.edwingonzalez.turismm.Clases.Noticia;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewNoticia;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewNoticia = findViewById(R.id.recyclerViewNoticia);
        ArrayList<Noticia> noticias = new ArrayList<>();
        noticias.add(new Noticia("Canal de Panamá", "andres Martinez", "detalles", 0, getDrawable(R.drawable.ig_canalpanama1), getDrawable(R.drawable.ic_usuario_andres_martinez)));
        noticias.add(new Noticia("Canal de Chorrera", "andres Arauz", "detalles", 0, getDrawable(R.drawable.ig_canalpanama1), getDrawable(R.drawable.ic_usuario_andres_martinez)));
        noticias.add(new Noticia("Canal de Colón", "andres Perez", "detalles", 0, getDrawable(R.drawable.ig_canalpanama1), getDrawable(R.drawable.ic_usuario_andres_martinez)));
        NoticiaAdaptador noticiaAdaptador = new NoticiaAdaptador(this, noticias);
        recyclerViewNoticia.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNoticia.setAdapter(noticiaAdaptador);
    }
}