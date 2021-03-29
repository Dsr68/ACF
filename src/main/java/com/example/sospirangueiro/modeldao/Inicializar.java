package com.example.sospirangueiro.modeldao;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Inicializar {
    public static FirebaseDatabase fd;
    public static DatabaseReference dr;

    public static DatabaseReference inicializar(Context context){
        FirebaseApp.initializeApp(context);
        fd = FirebaseDatabase.getInstance();
        dr = fd.getReference();

        return dr;
    }

}
