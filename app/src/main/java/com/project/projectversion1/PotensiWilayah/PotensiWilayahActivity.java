package com.project.projectversion1.PotensiWilayah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.projectversion1.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PotensiWilayahActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text1, text2;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potensi_wilayah);
        toolbar = findViewById(R.id.commToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Potensi Wilayah");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text1 = findViewById(R.id.toProklim);
        text2 = findViewById(R.id.toHutanBambu);
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toProklim:
                Intent a = new Intent(PotensiWilayahActivity.this,ProklimActivity.class);
                startActivity(a);
                break;
            case R.id.toHutanBambu:
                Intent b = new Intent(PotensiWilayahActivity.this,HutanBambuActivity.class);
                startActivity(b);
                break;
        }
    }
}