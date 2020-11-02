package com.project.projectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.project.projectversion1.Artikel.ArtikelActivity;
import com.project.projectversion1.InovasiPrestasi.InovasiPrestasiActivity;
import com.project.projectversion1.KasiKesos.KasiKesosActivity;
import com.project.projectversion1.KasiKesos.PosyanduActivity;
import com.project.projectversion1.KasiPemtrantibum.KasiPemtrantibum;
import com.project.projectversion1.KasiPemtrantibum.LinmasActivity;
import com.project.projectversion1.KasiPermasbang.BKMActivity;
import com.project.projectversion1.KasiPermasbang.KasiPermasbangActivity;
import com.project.projectversion1.Lokasi.LokasiActivity;
import com.project.projectversion1.PotensiWilayah.PotensiWilayahActivity;
import com.project.projectversion1.PotensiWilayah.ProklimActivity;
import com.project.projectversion1.Profile.DemografiActivity;
import com.project.projectversion1.Profile.ProfilActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CardView profile, artikel, kasipermasbang, kasipemratibum, kasikesos, inovasi, wisata, lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = findViewById(R.id.commToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Farmbahanyu");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        profile = findViewById(R.id.profileCard);
        artikel = findViewById(R.id.artikelCard);
        kasikesos = findViewById(R.id.kasiKesosCard);
        kasipemratibum = findViewById(R.id.kasiPermratibum);
        kasipermasbang = findViewById(R.id.kasiPermasbangCard);
        wisata = findViewById(R.id.wisataCard);
        lokasi = findViewById(R.id.lokasiCard);
        inovasi = findViewById(R.id.inovasiPrestasiCard);

        profile.setOnClickListener(this);
        artikel.setOnClickListener(this);
        wisata.setOnClickListener(this);
        lokasi.setOnClickListener(this);
        kasipemratibum.setOnClickListener(this);
        kasipermasbang.setOnClickListener(this);
        kasikesos.setOnClickListener(this);
        inovasi.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signOut:

                FirebaseAuth.getInstance().signOut();
                Intent out = new Intent(MenuActivity.this, LoginActivity.class);

                out.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                out.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(out);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.profileCard:
                i = new Intent(this, ProfilActivity.class);
                startActivity(i);
                break;
            case R.id.artikelCard:
                i = new Intent(this, ArtikelActivity.class);
                startActivity(i);
                break;
            case R.id.kasiPermasbangCard:
                i = new Intent(this, KasiPermasbangActivity.class);
                startActivity(i);
                break;
            case R.id.kasiPermratibum:
                i = new Intent(this, KasiPemtrantibum.class);
                startActivity(i);
                break;
            case R.id.kasiKesosCard:
                i = new Intent(this, KasiKesosActivity.class);
                startActivity(i);
                break;
            case R.id.lokasiCard:
                i = new Intent(this, LokasiActivity.class);
                startActivity(i);
                break;
            case R.id.wisataCard:
                i = new Intent(this, PotensiWilayahActivity.class);
                startActivity(i);
                break;
            case R.id.inovasiPrestasiCard:
                i = new Intent(this, InovasiPrestasiActivity.class);
                startActivity(i);
                break;




        }
    }
}