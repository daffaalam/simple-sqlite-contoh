package com.example.simplesqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class read extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    private database_handler databaseHandler;
    private List<data_siswa> siswaList = new ArrayList<data_siswa>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        listView = (ListView) findViewById(R.id.listDataLayout);

        databaseHandler = new database_handler(this);

        customListAdapter adapter = new customListAdapter(this, siswaList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setClickable(true);
        siswaList.clear();

        List<data_siswa> dataSiswas = databaseHandler.readData();
        for (data_siswa initData : dataSiswas) {
            data_siswa model = new data_siswa();
            model.setId_siswa(initData.getId_siswa());
            model.setNama_siswa(initData.getNama_siswa());
            model.setKelas_siswa(initData.getKelas_siswa());
            siswaList.add(model);

            if ((siswaList.isEmpty())) {
                Toast.makeText(read.this, "Tidak ada data dalam Table!!!", Toast.LENGTH_SHORT).show();
            } else {
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Object object = listView.getItemAtPosition(position);
        data_siswa itemDetail = (data_siswa) object;

        String idSiswa = itemDetail.getId_siswa();
        String namaSiswa = itemDetail.getNama_siswa();
        String kelasSiswa = itemDetail.getKelas_siswa();

        Intent pindah = new Intent(read.this, update_delete.class);
        pindah.putExtra("ID", idSiswa);
        pindah.putExtra("NAMA", namaSiswa);
        pindah.putExtra("KELAS", kelasSiswa);
        startActivity(pindah);

    }
}
