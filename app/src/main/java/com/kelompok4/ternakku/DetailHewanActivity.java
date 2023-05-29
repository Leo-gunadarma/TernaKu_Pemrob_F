package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.kelompok4.ternakku.database.DatabaseTernaku;

public class DetailHewanActivity extends AppCompatActivity {
    TextView detailNamaHewan,detailRasHewan,detailJenisHewan, detailJumlahHewan, detailJadwalMakan, detailKandangHewan,detailObatHewan;
    String kandangHewan,obatHewan;
    int kandangHewanId, obatHewanId;
    DatabaseTernaku database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hewan);
        detailNamaHewan = findViewById(R.id.textViewResultNamaHewan);
        detailRasHewan = findViewById(R.id.textViewResultRasHewan);
        detailJenisHewan = findViewById(R.id.textViewResultJenisHewan);
        detailJumlahHewan = findViewById(R.id.textViewResultJumlahHewan);
        detailJadwalMakan = findViewById(R.id.textViewResultJadwalMakan);
        detailKandangHewan = findViewById(R.id.textViewResultKandangHewan);
        detailObatHewan = findViewById(R.id.textViewResultObatHewan);
        database = DatabaseTernaku.getDbInstance(this.getApplicationContext());
        if (getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            detailNamaHewan.setText(hasil.getStringExtra("nama_hewan"));
            detailRasHewan.setText(hasil.getStringExtra("ras_hewan"));
            detailJenisHewan.setText(hasil.getStringExtra("jenis_hewan"));
            detailJumlahHewan.setText(hasil.getStringExtra("jumlah_hewan"));
            detailJadwalMakan.setText(hasil.getStringExtra("jadwal_makan"));
            kandangHewanId = Integer.parseInt(hasil.getStringExtra("kandang_id"));
            kandangHewan = database.daoHewan().getNamaKandangById(kandangHewanId);
            obatHewanId = Integer.parseInt(hasil.getStringExtra("obat_id"));
            obatHewan= database.daoHewan().getNamaObatById(obatHewanId);
            detailKandangHewan.setText(kandangHewan);
            detailObatHewan.setText(obatHewan);

        }
    }
}