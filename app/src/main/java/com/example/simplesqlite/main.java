package com.example.simplesqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main extends AppCompatActivity {

    //Deklarasi
    Button button_create, button_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ekstraksi
        button_create = (Button) findViewById(R.id.create);
        button_read = (Button) findViewById(R.id.read);

    }

    //Methode pindah ke activity Create New Data
    public void pindahCreate(View view) {
        startActivity(new Intent(this, create.class));
    }

    //Methode pindah ke activity Update Data
    public void pindahRead(View view) {
        startActivity(new Intent(this, read.class));
    }
}
