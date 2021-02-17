package com.himo.mismascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

public class MascotasFavoritas extends AppCompatActivity {

    ArrayList<Mascota> mascotasFavoritas;
    private RecyclerView rvMascotasFavoritas;
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
            public void onClick(View v) { finish(); }
        });
    }

    public void inicializarAdaptador() {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotasFavoritas, this);
        rvMascotasFavoritas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas() {
        mascotasFavoritas = getIntent().getParcelableArrayListExtra("mascotas");
        Collections.sort(mascotasFavoritas);
        mascotasFavoritas.remove(6);
        mascotasFavoritas.remove(5);
    }

}