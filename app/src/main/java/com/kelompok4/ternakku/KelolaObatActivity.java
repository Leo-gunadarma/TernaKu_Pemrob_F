package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelObat;

import java.util.ArrayList;
import java.util.List;

public class KelolaObatActivity extends AppCompatActivity {
    //    Membuat object database,recycleview, button dan adapter
    private DatabaseTernaku database;
    RecyclerView recyclerViewObat;
    KelolaObatAdpter adapter;
    FloatingActionButton addButton;

    //    Memabuat list yang menampung semua data yang ada pada tabel
    List<TabelObat> dataListObat = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_obat);

        //        Mengregistrasi object
        database = DatabaseTernaku.getDbInstance(this);
        recyclerViewObat = findViewById(R.id.recyclerViewKelolaObat);
        recyclerViewObat.setLayoutManager(new LinearLayoutManager(this));

//        Memanggil class show data
        showData();

//        Registrasi Button tumbah data
        addButton = findViewById(R.id.addButtonObat);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahData();
            }
        });
    }
    public void showData(){
        dataListObat = database.daoHewan().getAllDataObat();
        adapter = new KelolaObatAdpter(KelolaObatActivity.this, dataListObat);
        recyclerViewObat.setAdapter(adapter);
    }

    public void tambahData(){
        Intent intent=new Intent(KelolaObatActivity.this,TambahObatActivity.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
}