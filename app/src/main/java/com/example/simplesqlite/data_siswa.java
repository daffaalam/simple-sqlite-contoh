package com.example.simplesqlite;

public class data_siswa {

    //Deklarasikan id nama dan kelas
    private String id_siswa, nama_siswa, kelas_siswa;

    //Buat constructor awalnya
    public data_siswa(String id_siswa, String nama_siswa, String kelas_siswa) {
        this.id_siswa = id_siswa;
        this.nama_siswa = nama_siswa;
        this.kelas_siswa = kelas_siswa;
    }

    //Methode if nilai kosong
    public data_siswa() {
    }

    //SETTER dan GETTER
    public void setId_siswa(String id_siswa) {
        this.id_siswa = id_siswa;
    }

    public String getId_siswa() {
        return this.id_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getNama_siswa() {
        return this.nama_siswa;
    }

    public void setKelas_siswa(String kelas_siswa) {
        this.kelas_siswa = kelas_siswa;
    }

    public String getKelas_siswa() {
        return this.kelas_siswa;
    }

}
