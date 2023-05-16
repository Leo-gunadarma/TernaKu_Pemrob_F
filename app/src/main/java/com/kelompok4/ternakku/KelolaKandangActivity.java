package com.kelompok4.ternakku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelKandang;

import java.util.ArrayList;
import java.util.List;

public class KelolaKandangActivity extends AppCompatActivity {
//    Membuat object database,recycleview, button dan adapter
    private DatabaseTernaku database;
    RecyclerView recyclerViewKandang;
    KelolaKandangAdpater adapter;
    FloatingActionButton addButton;

//    Memabuat list yang menampung semua data yang ada pada tabel
    List<TabelKandang> dataListKandang = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_kandang);
//        Mengregistrasi object
        database = DatabaseTernaku.getDbInstance(this);
        recyclerViewKandang = findViewById(R.id.recyclerViewKelolaKandang);
        recyclerViewKandang.setLayoutManager(new LinearLayoutManager(this));

//        Memanggil class show data
        showData();

//        Registrasi Button tumbah data
        addButton = findViewById(R.id.addButtonKandang);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahData();
            }
        });
    }
    public void showData(){
        dataListKandang = database.daoHewan().getAllDataKandang();
        adapter = new KelolaKandangAdpater(KelolaKandangActivity.this,dataListKandang);
        recyclerViewKandang.setAdapter(adapter);
    }

    public void tambahData(){
        Intent intent=new Intent(KelolaKandangActivity.this,TambahKandangActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                String hasil = data.getStringExtra("nama_kandang");
                AlertDialog.Builder succesMsg = new AlertDialog.Builder(KelolaKandangActivity.this);
                succesMsg.setTitle("Inputan Berhasil");
                succesMsg.setMessage("Anda berhasil memasukan kandang \""+hasil+ "\" dalam menu kelola kandang");
                succesMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                succesMsg.show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
}