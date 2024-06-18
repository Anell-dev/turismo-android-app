package com.edwingonzalez.turismm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Detalles extends AppCompatActivity {
    TextView textViewTituloDetalle, textViewInfoDetalle;
    ImageButton imageButtonRegresar, imageButtonUbicacion, imageButtonCompartir, imageButtonTelefono;
    FloatingActionButton floatingActionButtonWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detalles_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //?---------------------Vinculamos titulo y le asignamos el texto--------------------\\
        textViewTituloDetalle = findViewById(R.id.textViewTituloDetalle);
        textViewTituloDetalle.setText(getIntent().getExtras().getString("titulo"));


        //?-------------------Vinculamos botonSalir y le damos la accion de salir----------------\\
        imageButtonRegresar = findViewById(R.id.imageButtonRegresar);
        imageButtonRegresar.setOnClickListener(view -> finish());


        //?-------------------Vinculamos y le asignamos el detalle--------------------------\\
        textViewInfoDetalle = findViewById(R.id.textViewInfoDetalle);
        textViewInfoDetalle.setText(getIntent().getExtras().getString("detalles"));


        //?------------Vinculamos el boton de ubicacion y le asignamos la busqueda refrente al titulo------------\\
        imageButtonUbicacion = findViewById(R.id.imageButtonUbicacion);
        imageButtonUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("google.navigation:q=Panama, " + getIntent().getExtras().getString("titulo"))) ;
                startActivity(intent);
            }
        });

        //?-------------Vinculamos el boton de compartir y creamos su funcionalidad------------------\\
        imageButtonCompartir = findViewById(R.id.imageButtonCompartir);
        imageButtonCompartir.setOnClickListener(view -> {
            Bitmap bitmap = captureScreen();
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Captura de pantalla", null);
            Uri uri = Uri.parse(path);
            shareImage(uri);
        });


        //?-------------Vinculamos el boton de llamada y creamos su funcionalidad--------------------\\
        imageButtonTelefono = findViewById(R.id.imageButtonTelefono);
        imageButtonTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:6225-5509"));
                startActivity(intent);
            }
        });



        //?------------Vinvulamos el boton de web y creamos su funcionalidad--------------------------\\
        floatingActionButtonWeb = findViewById(R.id.floatingActionButtonWeb);
        floatingActionButtonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/search?q=" + getIntent().getExtras().getString("titulo")));
                startActivity(intent);
            }
        });

    }

    //?---------------------Funcion para la captura de pantalla---------------------------\\
    private Bitmap captureScreen() {
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    //?---------------------Funcion para compartir la captura captura---------------------------\\
    private void shareImage(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Compartir imagen"));
    }
}