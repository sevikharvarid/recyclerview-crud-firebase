package com.project.projectversion1.KasiPemtrantibum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.projectversion1.R;

public class KasiPemtrantibum extends AppCompatActivity {

    private TextView textLinmas;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasi_pemtrantibum);
        toolbar = findViewById(R.id.commToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kasi Pemtrantibum");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        textLinmas = findViewById(R.id.toLinmas);
        textLinmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KasiPemtrantibum.this, LinmasActivity.class);
                startActivity(intent);
            }
        });


    }
}