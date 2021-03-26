package com.example.sospirangueiro.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sospirangueiro.R;

public class MetaActivy extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_activy);

        Spinner tempo = findViewById(R.id.spTempo);
        String lista[] = getResources().getStringArray(R.array.tempo);

        tempo.setAdapter(new ArrayAdapter<String>(getApplicationContext()
        , R.layout.support_simple_spinner_dropdown_item, lista));
    }
}