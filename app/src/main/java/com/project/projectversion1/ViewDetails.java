package com.project.projectversion1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ViewDetails extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView nameDetails, descDetails , titleDetails;
    private ImageView imageDetails,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        back = findViewById(R.id.back);
        nameDetails = findViewById(R.id.nameDetails);
        descDetails = findViewById(R.id.descDetails);
        titleDetails = findViewById(R.id.titleDetails);
        imageDetails = findViewById(R.id.imageDetails);
        getData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDetails.super.onBackPressed();
            }
        });
    }

    private void getData() {
        final String getGambar = getIntent().getExtras().getString("dataGambar");
        final String getDesc = getIntent().getExtras().getString("dataDescription");
        final String getTitle = getIntent().getExtras().getString("dataTitle");
        final String getUsernam = getIntent().getExtras().getString("dataUsername");
        nameDetails.setText(getUsernam);
        descDetails.setText(getDesc);
        titleDetails.setText(getTitle);
        Glide.with(this).load(getGambar).into(imageDetails);
    }
}