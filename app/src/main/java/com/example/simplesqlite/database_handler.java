package com.example.simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class database_handler extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data_akademik";

    private static final String table_siswa = "tbl_siswa";
    private static final String siswa_id = "id_siswa";
    private static final String siswa_name = "nama_siswa";
    private static final String siswa_class = "kelas_siswa";

    private static final String kueriCreatetable = "CREATE TABLE " + table_siswa + " ( " + siswa_id + " INTEGER PRIMARY KEY, " + siswa_name + " TEXT, " + siswa_class + " TEXT )";

    public database_handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(kueriCreatetable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<data_siswa> readData() {

        //Deklarasi inisialisasi dari file data_siswa
        List<data_siswa> list = new ArrayList<data_siswa>();

        //Variable tuk kueri membaca list data dalam table
        String kueriSELECT = "SELECT * FROM " + table_siswa;

        //Buat data SQLite dgn variable
        SQLiteDatabase database = this.getWritableDatabase();

        //Cursor tuk mngambil data dari database, dan menyimpan hasil kueri dalam rows
        Cursor cursor = database.rawQuery(kueriSELECT, null);

        if (cursor.moveToFirst()) {
            do {

                //Ambil data dari data_siswa dan list data dengan cursor
                data_siswa datas_siswa = new data_siswa();
                datas_siswa.setId_siswa(cursor.getString(0));
                datas_siswa.setNama_siswa(cursor.getString(1));
                datas_siswa.setKelas_siswa(cursor.getString(2));
                list.add(datas_siswa);
            }

            //Buat loopingnya
            while (cursor.moveToNext());
        }
        database.close();
        return list;
    }

    //Methode tuk create data
    public void createData(data_siswa getData) {

        //Deklarasikan SQLiteDatabase dan ContentValues
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Masukan data/nilai kedalam table
        values.put(siswa_id, getData.getId_siswa());
        values.put(siswa_name, getData.getNama_siswa());
        values.put(siswa_class, getData.getKelas_siswa());

        database.insert(table_siswa, null, values);
        database.close();
    }

    //Methode tuk read data
    public int updateData(data_siswa getData) {

        //Deklarasikan SQLiteDatabase dan ContentValues
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(siswa_name, getData.getNama_siswa());
        values.put(siswa_class, getData.getKelas_siswa());

        //Looping
        return database.update(table_siswa, values, siswa_id + " = ?", new String[]{
                String.valueOf(getData.getId_siswa())
        });
    }

    //Methode tuk delete data
    public void deleteData(data_siswa getData) {

        //Deklarasikan SQLiteDatabase
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(table_siswa, siswa_id + " = ?", new String[]{
                String.valueOf(getData.getId_siswa())
        });
        database.close();
    }
}
