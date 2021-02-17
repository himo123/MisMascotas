package com.himo.mismascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas = new ArrayList<>();
    private RecyclerView rvMascotas;

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
    }

    public void mostrarMascotasFavoritas (View v) {
        Intent intento = new Intent(this, MascotasFavoritas.class);
        intento.putParcelableArrayListExtra("mascotas", mascotas); //La clase Mascota implements Parcelable
        this.startActivity(intento);
    }
}