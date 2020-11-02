package com.project.projectversion1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button register;


    private EditText name, email, password1, password2, nik, tempatlahir, tanggallahir;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = findViewById(R.id.commToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrasi User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        name = findViewById(R.id.namaTxt);
        email = findViewById(R.id.emailTxt);
        nik = findViewById(R.id.nikTxt);
        tempatlahir = findViewById(R.id.tempatLahirTxt);
        tanggallahir = findViewById(R.id.tanggalLahirTxt);
        password1 = findViewById(R.id.password1Txt);
        password2 = findViewById(R.id.password2Txt);

        register = findViewById(R.id.btnSignUp);
        firebaseAuth = FirebaseAuth.getInstance();


        tanggallahir.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    tanggallahir.setText(current);
                    tanggallahir.setSelection(sel < current.length() ? sel : current.length());



                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();

                if (validate() && registerUser()) {
                    //input data to database
                    final String user_name = name.getText().toString().trim();
                    final String user_email = email.getText().toString().trim();
                    final String user_tgllahir = tanggallahir.getText().toString().trim();
                    final String user_tempatlahir = tempatlahir.getText().toString().trim();

                    final String user_nik = nik.getText().toString().trim();

                    String user_password = password1.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        user User = new user(
                                                user_name,
                                                user_email,
                                                user_tgllahir,
                                                user_tempatlahir,
                                                user_nik
                                        );
                                        FirebaseDatabase.getInstance().getReference("users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(User);
                                        sendEmail();

                                        Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));


                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Registration Gagal", Toast.LENGTH_SHORT).show();
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


        //  registersignin.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View view) {

        //        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);

        //  startActivity(i);
        //}
        //});

    }

    private Boolean validate() {
        Boolean result = false;

        String ni = nik.getText().toString();
        String tgl = tanggallahir.getText().toString();
        String tm = tempatlahir.getText().toString();
        String nam = name.getText().toString();
        String ema = email.getText().toString();
        String pass = password1.getText().toString();
        String pass2 = password2.getText().toString();

        if (nam.isEmpty() || ni.isEmpty() || tm.isEmpty() || tgl.isEmpty() || ema.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
            Context context = getApplicationContext();
            LayoutInflater inflater = getLayoutInflater();
            View customToastroot = inflater.inflate(R.layout.emptyfield_toast, null);
            Toast customToast = new Toast(context);

            customToast.setView(customToastroot);
            customToast.setDuration(Toast.LENGTH_LONG);
            customToast.show();

        } else {

            result = true;
        }

        return result;
    }

    private boolean registerUser() {
        Boolean result = false;


        String ni = nik.getText().toString();
        String tgl = tanggallahir.getText().toString();
        String tm = tempatlahir.getText().toString().toString();
        String nam = name.getText().toString();
        String ema = email.getText().toString();
        String pass = password1.getText().toString();
        String pass2 = password2.getText().toString();


        if (pass.length() < 6) {
            password1.setError("Password should be at least 6 characters long");
            password1.requestFocus();
            return false;
        }

        if (pass2.length() < 6) {
            password2.setError("Password should be at least 6 characters long");
            password2.requestFocus();
            return false;
        } else {
            result = true;
        }

        return result;
    }

    private void sendEmail() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registrasi Berhasil, Verifikasi di kirim ke Email", Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Verification Email tidak terkirim", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}


