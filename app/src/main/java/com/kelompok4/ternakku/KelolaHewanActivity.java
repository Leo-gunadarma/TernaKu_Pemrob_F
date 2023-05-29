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
import com.kelompok4.ternakku.database.TabelHewan;

import java.util.ArrayList;
import java.util.List;

public class KelolaHewanActivity extends AppCompatActivity {
    FloatingActionButton addButton;
    private DatabaseTernaku database;
    RecyclerView recyclerViewHewan;
    KelolaHewanAdapter adapter;
    List<TabelHewan> dataListHewan =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_hewan);

        //        Mengregistrasi object
        database = DatabaseTernaku.getDbInstance(this);
        recyclerViewHewan = findViewById(R.id.recyclerViewKelolaHewan);
        recyclerViewHewan.setLayoutManager(new LinearLayoutManager(this));

//        Memanggil class show data
        showData();

//        Registrasi Button tumbah data
        addButton = findViewById(R.id.addButtonHewan);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahData();
            }
        });
    }
    public void showData(){
        dataListHewan = database.daoHewan().getAllDataHewan();
        adapter = new KelolaHewanAdapter(KelolaHewanActivity.this,dataListHewan);
        recyclerViewHewan.setAdapter(adapter);
    }

    public void tambahData(){
        Intent intent=new Intent(KelolaHewanActivity.this,TambahHewanActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                String hasil = data.getStringExtra("nama_hewan");
                AlertDialog.Builder succesMsg = new AlertDialog.Builder(KelolaHewanActivity.this);
                succesMsg.setTitle("Inputan Berhasil");
                succesMsg.setMessage("Anda berhasil memasukan hewan \""+hasil+ "\" dalam menu kelola Hewan");
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