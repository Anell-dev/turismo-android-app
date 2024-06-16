package com.edwingonzalez.turismm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.content.ContextCompat;

import com.edwingonzalez.turismm.Adaptadores.NoticiaAdaptador;
import com.edwingonzalez.turismm.Clases.Noticia;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButtonSalir;
    RecyclerView recyclerViewNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupViews();
        setClickListeners();
        Datos();
    }

    private void setupViews() {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blanco));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageButtonSalir = findViewById(R.id.imageButtonSalir);
    }

    //-------------------------------Botones--------------------------------\\
    private void setClickListeners() {
        imageButtonSalir.setOnClickListener(view -> finishAffinity());
    }

    //-------------------------------Datos--------------------------------\\
    @SuppressLint("UseCompatLoadingForDrawables")
    private void Datos() {
        recyclerViewNoticia = findViewById(R.id.recyclerViewNoticia);
        ArrayList<Noticia> noticias = new ArrayList<>();
        noticias.add(new Noticia("Canal de Panamá", "Andrés Martinez", "detalles", 120, getDrawable(R.drawable.ig_canalpanama), getDrawable(R.drawable.ic_andres_martinez)));
        noticias.add(new Noticia("Casco Antiguo", "Laura González", "detalles", 75, getDrawable(R.drawable.ig_cascoantiguo), getDrawable(R.drawable.ic_laura_gonsalez)));
        noticias.add(new Noticia("Bocas del Toro", "Fernando Castillo", "detalles", 140, getDrawable(R.drawable.ig_bocasdeltoro), getDrawable(R.drawable.ic_fernando_castillo)));
        noticias.add(new Noticia("Portobelo", "Carolina Pérez", "detalles", 65, getDrawable(R.drawable.ig_portobelo), getDrawable(R.drawable.ic_carolina_perez)));
        noticias.add(new Noticia("Isla Coiba", "Juan Rodríguez", "detalles", 210, getDrawable(R.drawable.ig_islacoiba), getDrawable(R.drawable.ic_juan_rodriguez)));
        noticias.add(new Noticia("Volcán Baru", "Sofía Hernández", "detalles", 90, getDrawable(R.drawable.ig_volcanbaru), getDrawable(R.drawable.ic_sofia_hernandez)));
        noticias.add(new Noticia("El valle de Anton", "Miguel López", "detalles", 155, getDrawable(R.drawable.ig_valleanton), getDrawable(R.drawable.ic_miguel_lopez)));
        noticias.add(new Noticia("Isla San Blas", "Valeria Fernández", "detalles", 80, getDrawable(R.drawable.ig_islasanblas), getDrawable(R.drawable.ic_valeria_fernandez)));
        noticias.add(new Noticia("Museo de la Biodiversidad", "Pablo García", "detalles", 130, getDrawable(R.drawable.ig_museo), getDrawable(R.drawable.ic_pablo_garcia)));
        noticias.add(new Noticia("Cinta Costera", "Daniela Torres", "detalles", 50, getDrawable(R.drawable.ig_cintacostera), getDrawable(R.drawable.ic_daniela_torres)));
        NoticiaAdaptador noticiaAdaptador = new NoticiaAdaptador(this, noticias);
        recyclerViewNoticia.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNoticia.setAdapter(noticiaAdaptador);
    }
}