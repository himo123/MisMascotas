package com.himo.mismascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas = new ArrayList<>();
    private RecyclerView rvMascotas;
    private Mascota Mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar miToolBar = (Toolbar) findViewById(R.id.miToolBar);
        setSupportActionBar(miToolBar);

        rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();
    }

    public void inicializarAdaptador() {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        rvMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas() {
        mascotas.add(new Mascota("Katuska", "Alaska Malamute", R.drawable.alaska_malamute, 0));
        mascotas.add(new Mascota("Dakota", "Beagle", R.drawable.beagle, 0));
        mascotas.add(new Mascota("Benji", "Cocker Spaniel", R.drawable.cocker, 0));
        mascotas.add(new Mascota("Apollo", "Husky Siberiano", R.drawable.husky_siberiano, 0));
        mascotas.add(new Mascota("Scoop", "Labrador", R.drawable.labrador, 0));
        mascotas.add(new Mascota("Geiser", "Pastor Alemán", R.drawable.pastor_aleman, 0));
        mascotas.add(new Mascota("Manchas", "Yorkshire Terrier", R.drawable.yorkshire, 0));

        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            int[] ratings = parametros.getIntArray("ratings");
            int i = 0;
            for (Mascota m : mascotas) {
                m.setRating(ratings[i]);
                i++;
            }
        }
    }

    public void mostrarMascotasFavoritas (View v) {
        int[] ratings = new int[7];
        int i = 0;
        for (Mascota m : mascotas) {
            ratings[i] = m.getRating();
            i++;
        }

        Intent intento = new Intent(this, MascotasFavoritas.class);
        Collections.sort(mascotas);
        Mascota = mascotas.get(0);
        intento.putExtra("mascota0nombre", Mascota.getNombre());
        intento.putExtra("mascota0rating", Mascota.getRating());
        Mascota = mascotas.get(1);
        intento.putExtra("mascota1nombre", Mascota.getNombre());
        intento.putExtra("mascota1rating", Mascota.getRating());
        Mascota = mascotas.get(2);
        intento.putExtra("mascota2nombre", Mascota.getNombre());
        intento.putExtra("mascota2rating", Mascota.getRating());
        Mascota = mascotas.get(3);
        intento.putExtra("mascota3nombre", Mascota.getNombre());
        intento.putExtra("mascota3rating", Mascota.getRating());
        Mascota = mascotas.get(4);
        intento.putExtra("mascota4nombre", Mascota.getNombre());
        intento.putExtra("mascota4rating", Mascota.getRating());

        intento.putExtra("ratings", ratings);
        this.startActivity(intento);
    }
}