package com.project.projectversion1.KasiPermasbang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.projectversion1.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class KasiPermasbangActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text1, text2, text3, text4, text5, text6, text7;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasi_permasbang);
        toolbar = findViewById(R.id.commToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kasi Permasbang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text1 = findViewById(R.id.toBkm);
        text2 = findViewById(R.id.toFkdm);
        text3 = findViewById(R.id.toFkrw);
        text4 = findViewById(R.id.toPSM);
        text5 = findViewById(R.id.toPokdar);
        text6 = findViewById(R.id.toLDM);
        text7 = findViewById(R.id.toMub);

        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        text5.setOnClickListener(this);
        text6.setOnClickListener(this);
        text7.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toBkm:
                Intent a = new Intent(KasiPermasbangActivity.this,BKMActivity.class);
                startActivity(a);
                break;
            case R.id.toFkdm:
                Intent b = new Intent(KasiPermasbangActivity.this,FkdmActivity.class);
                startActivity(b);
                break;
            case R.id.toFkrw:
                Intent c = new Intent(KasiPermasbangActivity.this,FkrwActivity.class);
                startActivity(c);
                break;
            case R.id.toLDM:
                Intent d = new Intent(KasiPermasbangActivity.this,LpmActivity.class);
                startActivity(d);
                break;
            case R.id.toPokdar:
                Intent e = new Intent(KasiPermasbangActivity.this,PokdarActivity.class);
                startActivity(e);
                break;
            case R.id.toPSM:
                Intent f = new Intent(KasiPermasbangActivity.this,PsmActivity.class);
                startActivity(f);
                break;
            case R.id.toMub:
                Intent g = new Intent(KasiPermasbangActivity.this,MubActivity.class);
                startActivity(g);
                break;

        }
    }
}