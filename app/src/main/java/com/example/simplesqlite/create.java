package com.example.simplesqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create extends AppCompatActivity {

    //Deklarasi EditText, Button, database_handler dan String tuk tampung data
    private EditText nama, kelas;
    private String snama, skelas;
    private database_handler databaseHandler;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

//        databaseHandler = new database_handler(this);

        //Ekstaksi EditText dan Button
        nama = (EditText) findViewById(R.id.nama);
        kelas = (EditText) findViewById(R.id.kelas);
        simpan = (Button) findViewById(R.id.save);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                snama = String.valueOf(nama.getText());
                skelas = String.valueOf(kelas.getText());

                if (snama.equals("")) {
                    nama.requestFocus();
                    Toast.makeText(create.this, "Nama tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                } else if (skelas.equals("")) {
                    kelas.requestFocus();
                    Toast.makeText(create.this, "Kelas tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                } else {
                    nama.setText("");
                    kelas.setText("");
                    Toast.makeText(create.this, "Data berhasil dimasukan", Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(create.this);
                    builder
                            .setTitle("Notification")
                            .setMessage("data sudah berhasil masuk\n\nNama : " + snama + "\nKelas : " + skelas)
                            .setPositiveButton("Lihat Database", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(create.this, read.class));
                                }
                            })
                            .setNegativeButton("Kembail ke Beranda", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(create.this, main.class));
                                }
                            });
                    builder.create().show();
                }
            }
        });
    }
}
