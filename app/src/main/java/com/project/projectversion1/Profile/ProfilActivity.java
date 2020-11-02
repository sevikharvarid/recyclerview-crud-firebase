package com.project.projectversion1.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.projectversion1.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text1, text2, text3, text4, text5, text6;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        toolbar = findViewById(R.id.commToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text1 = findViewById(R.id.toVM);
        text2 = findViewById(R.id.toDemografi);
        text3 = findViewById(R.id.toKondisiF);
        text4 = findViewById(R.id.toSusunanKep);
        text5 = findViewById(R.id.toTabelJumlahPP);
        text6 = findViewById(R.id.toPendudukBerd);

        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        text5.setOnClickListener(this);
        text6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toVM:
                Intent a = new Intent(ProfilActivity.this, VisiMisiActivity.class);
                startActivity(a);
                break;
            case R.id.toDemografi:
                Intent b = new Intent(ProfilActivity.this, DemografiActivity.class);
                startActivity(b);
                break;
            case R.id.toSusunanKep:
                Intent c = new Intent(ProfilActivity.this, SusunanKepActivity.class);
                startActivity(c);
                break;
            case R.id.toPendudukBerd:
                Intent d = new Intent(ProfilActivity.this, PendudukBerAgActivity.class);
                startActivity(d);
                break;
            case R.id.toTabelJumlahPP:
                Intent e = new Intent(ProfilActivity.this, TabelJumlahPegPActivity.class);
                startActivity(e);
                break;
            case R.id.toKondisiF:
                Intent f = new Intent(ProfilActivity.this, KondisiFisikActivity.class);
                startActivity(f);
                break;
        }
    }
}