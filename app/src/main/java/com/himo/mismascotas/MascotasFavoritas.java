package com.himo.mismascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

public class MascotasFavoritas extends AppCompatActivity {

    ArrayList<Mascota> mascotasFavoritas, borrarMascotas;
    private RecyclerView rvMascotasFavoritas;

    String[] mascotaNombre = new String[7];
    int[] mascotaRating = new int[7];
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);
        contexto = this;

        Toolbar miToolBar = (Toolbar) findViewById(R.id.miToolBar);
        setSupportActionBar(miToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //muestra la flecha de regreso (se declara el parent en AndroidManifest)

        Button button_favoritos = (Button) findViewById(R.id.btn_favoritos);
        button_favoritos.setVisibility(View.INVISIBLE);

        rvMascotasFavoritas = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);
        LinearLayoutManager llm = new LinearLayoutManager(contexto);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotasFavoritas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();

        miToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento2 = new Intent(contexto, MainActivity.class);
                int[] ratings = getIntent().getIntArrayExtra("ratings");
                intento2.putExtra("ratings", ratings);
                contexto.startActivity(intento2);
            }
        });
    }

    public void inicializarAdaptador() {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotasFavoritas, this);
        rvMascotasFavoritas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas() {
        mascotasFavoritas = new ArrayList<>();   //Inicializa el ArrayList usando la misma información de MainActivity
        mascotasFavoritas.add(new Mascota("Katuska", "Alaska Malamute",   R.drawable.alaska_malamute, 0));
        mascotasFavoritas.add(new Mascota("Dakota",  "Beagle",            R.drawable.beagle,          0));
        mascotasFavoritas.add(new Mascota("Benji",   "Cocker Spaniel",    R.drawable.cocker,          0));
        mascotasFavoritas.add(new Mascota("Apollo",  "Husky Siberiano",   R.drawable.husky_siberiano, 0));
        mascotasFavoritas.add(new Mascota("Scoop",   "Labrador",          R.drawable.labrador,        0));
        mascotasFavoritas.add(new Mascota("Geiser",  "Pastor Alemán",     R.drawable.pastor_aleman,   0));
        mascotasFavoritas.add(new Mascota("Manchas", "Yorkshire Terrier", R.drawable.yorkshire,       0));

        //captura los parámetros enviados por MainActivity con el nombre y el rating de las 5 mascotas con más likes
        mascotaNombre[0] = (String) getIntent().getStringExtra("mascota0nombre");
        mascotaRating[0] = (int)    getIntent().getIntExtra("mascota0rating",0);
        mascotaNombre[1] = (String) getIntent().getStringExtra("mascota1nombre");
        mascotaRating[1] = (int)    getIntent().getIntExtra("mascota1rating",0);
        mascotaNombre[2] = (String) getIntent().getStringExtra("mascota2nombre");
        mascotaRating[2] = (int)    getIntent().getIntExtra("mascota2rating",0);
        mascotaNombre[3] = (String) getIntent().getStringExtra("mascota3nombre");
        mascotaRating[3] = (int)    getIntent().getIntExtra("mascota3rating",0);
        mascotaNombre[4] = (String) getIntent().getStringExtra("mascota4nombre");
        mascotaRating[4] = (int)    getIntent().getIntExtra("mascota4rating",0);

        //Asigna el rating (parámetros) a su correspondiente mascota dentro del ArrayList
        for (Mascota mf : mascotasFavoritas) {
            if (mf.getNombre() != null) {
                for (int i = 0; i < 5; i++) {
                    if (mf.getNombre().equals(mascotaNombre[i])) {
                        mf.setRating(mascotaRating[i]);
                    }
                }
            }
        }

//        borrarMascotas = new ArrayList<Mascota>();
//        for (Mascota mf : mascotasFavoritas) {  //Busca todas las mascotas con Rating=0 y las almacena en el ArrayList
//            if (mf.getRating() == 0) {
//                borrarMascotas.add(mf);
//            }
//        }
//
//        for (Mascota mf : borrarMascotas) {     //borra las mascotas almacenadas en el ArrayList
//                mascotasFavoritas.remove(mf);
//        }

        Collections.sort(mascotasFavoritas);
        mascotasFavoritas.remove(6);
        mascotasFavoritas.remove(5);
    }

}