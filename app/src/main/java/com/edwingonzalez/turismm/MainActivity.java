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
import com.edwingonzalez.turismm.Clases.DetallesUtil;
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

    //?-------------------------------Botones--------------------------------\\
    private void setClickListeners() {
        imageButtonSalir.setOnClickListener(view -> finishAffinity());
    }

    //?-------------------------------Datos--------------------------------\\
    @SuppressLint("UseCompatLoadingForDrawables")
    private void Datos() {
        recyclerViewNoticia = findViewById(R.id.recyclerViewNoticia);
        ArrayList<Noticia> noticias = new ArrayList<>();

        //?-------------Añadiendo noticias a la lista usando la función auxiliar----------\\
        addNoticia(noticias, "Canal de Panamá", "Andrés Martinez", DetallesUtil.CANAL_PANAMA, 120, R.drawable.ig_canalpanama, R.drawable.ic_andres_martinez);
        addNoticia(noticias, "Casco Antiguo", "Laura González", DetallesUtil.CASCO_ANTIGUO, 75, R.drawable.ig_cascoantiguo, R.drawable.ic_laura_gonsalez);
        addNoticia(noticias, "Bocas del Toro", "Fernando Castillo", DetallesUtil.BOCAS_DEL_TORO, 140, R.drawable.ig_bocasdeltoro, R.drawable.ic_fernando_castillo);
        addNoticia(noticias, "Portobelo", "Carolina Pérez", DetallesUtil.PORTOBELO, 65, R.drawable.ig_portobelo, R.drawable.ic_carolina_perez);
        addNoticia(noticias, "Isla Coiba", "Juan Rodríguez", DetallesUtil.ISLA_COIBA, 210, R.drawable.ig_islacoiba, R.drawable.ic_juan_rodriguez);
        addNoticia(noticias, "Volcán Baru", "Sofía Hernández", DetallesUtil.VOLCAN_BARU, 90, R.drawable.ig_volcanbaru, R.drawable.ic_sofia_hernandez);
        addNoticia(noticias, "El valle de Anton", "Miguel López", DetallesUtil.VALLE_ANTON, 155, R.drawable.ig_valleanton, R.drawable.ic_miguel_lopez);
        addNoticia(noticias, "Isla San Blas", "Valeria Fernández", DetallesUtil.ISLA_SAN_BLAS, 80, R.drawable.ig_islasanblas, R.drawable.ic_valeria_fernandez);
        addNoticia(noticias, "Museo de la Biodiversidad", "Pablo García", DetallesUtil.MUSEO_BIODIVERSIDAD, 130, R.drawable.ig_museo, R.drawable.ic_pablo_garcia);
        addNoticia(noticias, "Cinta Costera", "Daniela Torres", DetallesUtil.CINTA_COSTERA, 50, R.drawable.ig_cintacostera, R.drawable.ic_daniela_torres);

        NoticiaAdaptador noticiaAdaptador = new NoticiaAdaptador(this, noticias);
        recyclerViewNoticia.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNoticia.setAdapter(noticiaAdaptador);
    }

    //?---------------Función auxiliar para añadir noticias a la lista-----------------------\\
    private void addNoticia(ArrayList<Noticia> noticias, String titulo, String autor, String detalles, int likes, int imagenPrincipal, int imagenAutor) {
        noticias.add(new Noticia(titulo, autor, detalles, likes, getDrawable(imagenPrincipal), getDrawable(imagenAutor)));
    }
}