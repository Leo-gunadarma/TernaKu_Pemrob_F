package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelKandang;

import java.util.ArrayList;
import java.util.List;

public class TambahHewanActivity extends AppCompatActivity {
    private DatabaseTernaku database;
    EditText namaHewan, rasHewan, jumlahHewan;
    Spinner spinnerJenisHewan,spinnerPilihKandang,spinnerJadwalMakan;
    Button simpanHewan;
    TextView judulForm;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_hewan);
        database = DatabaseTernaku.getDbInstance(this);
//       Registrasi Object
        namaHewan = findViewById(R.id.editTextNamaHewan);
        rasHewan = findViewById(R.id.editTextRasHewan);
        jumlahHewan= findViewById(R.id.editTextJumlahHewan);
        spinnerJenisHewan = findViewById(R.id.spinnerJenisHewan);
        spinnerJadwalMakan = findViewById(R.id.spinnerJadwalMakan);
        spinnerPilihKandang = findViewById(R.id.spinnerPilihKandang);
        simpanHewan = findViewById(R.id.buttonSimpanHewan);
        judulForm = findViewById(R.id.textView9);



//        Mengambil semua data nama kandang dan menaruhnya kedalam spinner
        List<String> kandangList = database.daoHewan().getAllNamaKandang();
        kandangList.add(0, "Pilih Kandang");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kandangList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPilihKandang.setAdapter(spinnerAdapter);


//        Ketika tombol simpan dipilih
        simpanHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //        Validasi Input
                if (namaHewan.getText().toString().trim().length() == 0) {
                    namaHewan.setError("Nama Hewan harus di isi!");
                    namaHewan.requestFocus();
                } else if (rasHewan.getText().toString().trim().length() == 0) {
                    rasHewan.setError("Ras Hewan Harus di isi!");
                    rasHewan.requestFocus();
                } else if (jumlahHewan.getText().toString().trim().length() == 0) {
                    jumlahHewan.setError("Jumlah Hewan Harus Di isi!");
                    jumlahHewan.requestFocus();
                }else if(spinnerJenisHewan.getSelectedItemPosition()==0){
                    Toast.makeText(TambahHewanActivity.this,"Jenis Hewan Belum dirubah",Toast.LENGTH_LONG).show();
                    spinnerJenisHewan.requestFocus();
                }else if(spinnerJadwalMakan.getSelectedItemPosition()==0){
                    Toast.makeText(TambahHewanActivity.this,"Jadwal Makan Belum dirubah",Toast.LENGTH_LONG).show();
                    spinnerJadwalMakan.requestFocus();
                } else if(spinnerPilihKandang.getSelectedItemPosition()==0){
                    Toast.makeText(TambahHewanActivity.this,"Pilih Kandang Belum dirubah",Toast.LENGTH_LONG).show();
                    spinnerPilihKandang.requestFocus();
                } else {
                    Toast.makeText(TambahHewanActivity.this,"Data berhasil disimpan",Toast.LENGTH_LONG).show();
                }
            }
        });


//
//        String namaKandangTerpilih = spinnerPilihKandang.getSelectedItem().toString();
//        int kandangIdTerpilih = database.daoHewan().getKandangIdByNama(namaKandangTerpilih);

    }
}