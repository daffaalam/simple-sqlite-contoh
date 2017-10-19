package com.example.simplesqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class customListAdapter extends BaseAdapter{

    //Deklarasikan Activity, layoutInflater, dan ListItemData
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<data_siswa> itemList;

    public customListAdapter(read read, List<data_siswa> siswaList) {

        //Definisikan dalam constructor
        this.activity = read;
        this.itemList = siswaList;

    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position)  {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null)
            layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.list_item, null);

        TextView nama_siswa = (TextView)convertView.findViewById(R.id.nama_lay);
        TextView kelas_siswa = (TextView)convertView.findViewById(R.id.kelas_lay);

        //Ambil data
        data_siswa getData = itemList.get(position);

        //Masukan data
        nama_siswa.setText("" + getData.getNama_siswa());
        kelas_siswa.setText("" + getData.getKelas_siswa());

        return convertView;
    }
}
