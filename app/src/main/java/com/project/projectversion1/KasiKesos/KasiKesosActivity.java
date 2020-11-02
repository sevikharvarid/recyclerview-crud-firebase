package com.project.projectversion1.KasiKesos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.projectversion1.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class KasiKesosActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text1, text2,text3;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasi_kesos);

        toolbar = findViewById(R.id.commToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kasi Kesos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text1 = findViewById(R.id.toPosyandu);
        text2 = findViewById(R.id.toPKK);
        text3 = findViewById(R.id.toKarangTaruna);
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toPKK:
                Intent a = new Intent(KasiKesosActivity.this,PKKActivity.class);
                startActivity(a);
                break;
            case R.id.toPosyandu:
                Intent b = new Intent(KasiKesosActivity.this,PosyanduActivity.class);
                startActivity(b);
                break;
            case R.id.toKarangTaruna:
                Intent c = new Intent(KasiKesosActivity.this,KarangTarunaActivity.class);
                startActivity(c);
                break;
        }
    }
}