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

public class update_delete extends AppCompatActivity {

    private database_handler handler;
    private String sid, sname, skelas;
    private EditText nama, kelas;
    private Button update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        //Menghubungkan database
        handler = new database_handler(this);

        //Menangkap data dari Intent
        sid = this.getIntent().getStringExtra("ID");
        sname = this.getIntent().getStringExtra("NAMA");
        skelas = this.getIntent().getStringExtra("KELAS");

        //Ekstraksiasi EditText dan Button
        nama = (EditText) findViewById(R.id.nama_layx);
        kelas = (EditText) findViewById(R.id.kelas_layx);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        //setText EditText
        nama.setText(sname);
        kelas.setText(skelas);

        //onClick button update utk mengupdate database
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname = String.valueOf(nama.getText());
                skelas = String.valueOf(kelas.getText());

                if (sname.equals("")) {
                    nama.requestFocus();
                    Toast.makeText(update_delete.this, "Nama tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                } else if (skelas.equals("")) {
                    kelas.requestFocus();
                    Toast.makeText(update_delete.this, "Kelas tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(update_delete.this, "Data berhasil diperbaharui", Toast.LENGTH_SHORT).show();

                    handler.updateData(new data_siswa(null, sname, skelas));

                    AlertDialog.Builder builder = new AlertDialog.Builder(update_delete.this);
                    builder
                            .setTitle("Notification")
                            .setMessage("data sudah berhasil diperbaharui\n\nNama : " + sname + "\nKelas : " + skelas +"\n")
                            .setPositiveButton("lihat database", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(update_delete.this, read.class));
                                    finish();
                                }
                            })
                            .setNegativeButton("edit lagi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    builder.create().show();
                }
            }
        });

        //onClick button delete dan peringatan menghapus data
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(update_delete.this);
                builder
                        .setTitle("Warning!")
                        .setMessage("data akan dihapus\n\nNama : " + sname + "\nKelas : " + skelas + "\nApakah Anda yakin menghapusnya?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                handler.deleteData(new data_siswa(sid, sname, skelas));
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.create().show();
            }
        });
    }
}
