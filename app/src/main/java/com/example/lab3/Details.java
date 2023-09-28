package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lab3.adapters.ContactAdapter;

import java.util.Arrays;

public class Details extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    public static Intent getIntent(Context applicationContext) {
        return new Intent(applicationContext, Details.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper db = new DatabaseHelper(this);

        String[] allUser = db.getAllUser();
        adapter = new ContactAdapter(allUser);
        recyclerView.setAdapter(adapter);


    }
}