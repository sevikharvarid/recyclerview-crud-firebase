package com.project.projectversion1.KasiPermasbang;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.projectversion1.Post;
import com.project.projectversion1.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MubActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton button;
    private RecyclerView recyclerView;
    private List<Post> postList;
    private MubAdapter mubAdapter;
    private EditText searchBar;

    private List<String> followingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mub);

        toolbar = findViewById(R.id.commToolbar);
        button = findViewById(R.id.addPosting);
        recyclerView = findViewById(R.id.datalist);
        searchBar = findViewById(R.id.search_field);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        postList = new ArrayList<>();
        mubAdapter = new MubAdapter(this, postList);
        recyclerView.setAdapter(mubAdapter);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MUB");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MubActivity.this, MubPostActivity.class);
                startActivity(i);
            }
        });

        readPosts();

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchReadPosts(editable.toString().toLowerCase());
            }
        });


        FirebaseAuth auth = FirebaseAuth.getInstance();

        if(auth.getUid()==null){
            button.setVisibility(View.GONE);
        }else{
            button.setVisibility(View.VISIBLE);
        }
    }


    private void readPosts() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("mubPosts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);
                    postList.add(post);
                }
                mubAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void searchReadPosts(final String search) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("mubPosts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);
                    if (post.getTitle().toLowerCase().contains(search)) {
                        postList.add(post);
                    }
                }
                mubAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}