package com.project.projectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ForgotActivity extends AppCompatActivity {

    EditText emailcheck;
    Button emailsend;


    FirebaseAuth firebaseAuth;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        toolbar = findViewById(R.id.commToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lupa Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        emailcheck=findViewById(R.id.etcheckemail);

        emailsend=findViewById(R.id.btnemailsend);


        firebaseAuth= FirebaseAuth.getInstance();

        emailsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_email=emailcheck.getText().toString().trim();

                if(user_email.equals(""))
                {
                    Toast.makeText(ForgotActivity.this,"Harap mengisi email yang terdaftar",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotActivity.this,"Reset email terkirim",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotActivity.this,LoginActivity.class));
                            }else{

                                Toast.makeText(ForgotActivity.this,"Error in sending reset password email",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();



    }
}
