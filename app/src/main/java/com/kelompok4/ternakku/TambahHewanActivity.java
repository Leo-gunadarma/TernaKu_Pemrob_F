package com.kelompok4.ternakku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelHewan;
import com.kelompok4.ternakku.database.TabelKandang;

import java.util.ArrayList;
import java.util.List;

public class TambahHewanActivity extends AppCompatActivity {
    private DatabaseTernaku database;
    EditText namaHewan, rasHewan, jumlahHewan;
    Spinner spinnerJenisHewan,spinnerPilihKandang,spinnerJadwalMakan, spinnerPilihObat;
    Button simpanHewan;
    TextView judulForm;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_hewan);

        //       Registrasi Object
        namaHewan = findViewById(R.id.editTextNamaHewan);
        rasHewan = findViewById(R.id.editTextRasHewan);
        jumlahHewan= findViewById(R.id.editTextJumlahHewan);
        spinnerJenisHewan = findViewById(R.id.spinnerJenisHewan);
        spinnerJadwalMakan = findViewById(R.id.spinnerJadwalMakan);
        spinnerPilihKandang = findViewById(R.id.spinnerPilihKandang);
        spinnerPilihObat = findViewById(R.id.spinnerPilihObat);
        simpanHewan = findViewById(R.id.buttonSimpanHewan);
        judulForm = findViewById(R.id.textView9);

        database = DatabaseTernaku.getDbInstance(this);
//        Mengecek apakah database dibuka dan sudah dibuat
        if(database.daoHewan().getKandangCount()==0){
            AlertDialog.Builder validasiMsgKandang = new AlertDialog.Builder(TambahHewanActivity.this);
            validasiMsgKandang.setCancelable(false);
            validasiMsgKandang.setTitle("Data Kandang Kosong");
            validasiMsgKandang.setMessage("Data Kandang Belum di isi,"+"\n"+
                    "Harap di isi terlibih dahulu");
            validasiMsgKandang.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(TambahHewanActivity.this, TambahKandangActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            validasiMsgKandang.show();
        }else if (database.daoHewan().getObatCount()==0){
            AlertDialog.Builder validasiMsgObat = new AlertDialog.Builder(TambahHewanActivity.this);
            validasiMsgObat.setCancelable(false);
            validasiMsgObat.setTitle("Data Obat Kosong");
            validasiMsgObat.setMessage("Data Obat Belum di isi,"+"\n"+
                    "Harap di isi terlibih dahulu");
            validasiMsgObat.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(TambahHewanActivity.this, TambahObatActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            validasiMsgObat.show();
        }else{
//        Mengambil semua data nama kandang dan menaruhnya kedalam spinner
            List<String> kandangList = database.daoHewan().getAllNamaKandang();
            kandangList.add(0, "Pilih Kandang");
            ArrayAdapter<String> spinnerAdapterKandang = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kandangList);
            spinnerAdapterKandang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerPilihKandang.setAdapter(spinnerAdapterKandang);


//        Mengambil semua data nama obat dan menaruhnya kedalam spinner
            List<String> obatList = database.daoHewan().getAllNamaObat();
            obatList.add(0, "Pilih Obat");
            ArrayAdapter<String> spinnerAdapterObat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obatList);
            spinnerAdapterObat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerPilihObat.setAdapter(spinnerAdapterObat);

            if(getIntent().getExtras()!= null){
                Intent hasil = getIntent();
                int id_hewan = Integer.parseInt(hasil.getStringExtra("id_hewan"));
                namaHewan.setText(hasil.getStringExtra("nama_hewan"));
                rasHewan.setText(hasil.getStringExtra("ras_hewan"));
                jumlahHewan.setText(hasil.getStringExtra("jumlah_hewan"));
                judulForm.setText("Form Perbarui Hewan");
                simpanHewan.setText("Perbarui Data");

                int jenisHewanIdSpinner = 0;
                String jenisHewanEdit = hasil.getStringExtra("jenis_hewan");
                String bertelur= "Ovipar (Bertelur)";
                String melahirkan = "Vivipar (Malahirkan)";
                String bertelurMelahirkan = "Ovovivipar (Bertelur dan Melahirkan)";
                if (jenisHewanEdit.equals(bertelur)){
                    jenisHewanIdSpinner = 1;
                }else if (jenisHewanEdit.equals(melahirkan)){
                    jenisHewanIdSpinner = 2;
                }else if (jenisHewanEdit.equals(bertelurMelahirkan)){
                    jenisHewanIdSpinner = 3;
                }
                spinnerJenisHewan.setSelection(jenisHewanIdSpinner);

                int jadwalMakanHewanId = 0;
                String jadwalMakan = hasil.getStringExtra("jadwal_makan");
                String pagi= "Pagi-Siang";
                String siang = "Siang-Sore";
                String sore = "Pagi-Sore";
                if (jadwalMakan.equals(pagi)){
                    jadwalMakanHewanId = 1;
                }else if (jadwalMakan.equals(siang)){
                    jadwalMakanHewanId = 2;
                }else if (jadwalMakan.equals(sore)){
                    jadwalMakanHewanId = 3;
                }
                spinnerJadwalMakan.setSelection(jadwalMakanHewanId);
                int kandang_id=Integer.parseInt(hasil.getStringExtra("kandang_id"));
                String namaKandang = database.daoHewan().getNamaKandangById(kandang_id);
                for (int i = 0; i < kandangList.size(); i++) {
                    if (kandangList.get(i).equals(namaKandang)) {
                        spinnerPilihKandang.setSelection(i);
                        break;
                    }
                }
                int obat_id=Integer.parseInt(hasil.getStringExtra("obat_id"));
                String namaObat = database.daoHewan().getNamaObatById(obat_id);
                for (int i = 0; i < obatList.size(); i++) {
                    if (obatList.get(i).equals(namaObat)) {
                        spinnerPilihObat.setSelection(i);
                        break;
                    }
                }

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
                        }else if(spinnerPilihObat.getSelectedItemPosition()==0){
                            Toast.makeText(TambahHewanActivity.this,"Pilih Obat Belum dirubah",Toast.LENGTH_LONG).show();
                            spinnerPilihObat.requestFocus();
                        } else {
                            updateData(id_hewan);
                        }
                    }
                });

            }else{
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
                        }else if(spinnerPilihObat.getSelectedItemPosition()==0){
                            Toast.makeText(TambahHewanActivity.this,"Pilih Obat Belum dirubah",Toast.LENGTH_LONG).show();
                            spinnerPilihObat.requestFocus();
                        } else {
                            simpanData();
                        }
                    }
                });
            }
        }

    }


    //    Menyimpan data hewan
    public void simpanData(){
        AlertDialog.Builder infoMsg = new AlertDialog.Builder(TambahHewanActivity.this);
        infoMsg.setTitle("Apakah Anda Yakin?");
        infoMsg.setMessage("Apakah anda yakin ingin menginputkan data sebagai berikut?\n\n"+"" +
                "Nama Hewan: " + namaHewan.getText().toString() +"\n"+
                "Ras Hewan: " + rasHewan.getText().toString() +"\n"+
                "Jumlah Hewan: " + jumlahHewan.getText().toString()+"\n"+
                "Jenis Hewan: " + spinnerJenisHewan.getSelectedItem().toString() +"\n"+
                "Jadwal Makan: " +spinnerJadwalMakan.getSelectedItem().toString() +"\n"+
                "Tempat Hewan: " + spinnerPilihKandang.getSelectedItem().toString() +"\n"+
                "Obat Hewan: " + spinnerPilihObat.getSelectedItem().toString() +"\n");
        infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String namaHewanVar = namaHewan.getText().toString();
                String rasHewanVar = rasHewan.getText().toString();
                String jumlahHewanVar = jumlahHewan.getText().toString();
                int jumlahHewanVarPar = Integer.parseInt(jumlahHewanVar);
                String jenisHewanVar = spinnerJenisHewan.getSelectedItem().toString();
                String jadwalMakanVar = spinnerJadwalMakan.getSelectedItem().toString();
                String namaKandangTerpilih = spinnerPilihKandang.getSelectedItem().toString();
                int kandangIdTerpilih = database.daoHewan().getKandangIdByNama(namaKandangTerpilih);
                String namaObatTerpilih = spinnerPilihObat.getSelectedItem().toString();
                int obatIdTerpilih = database.daoHewan().getObatIdByNama(namaObatTerpilih);
                TabelHewan row = new TabelHewan(namaHewanVar,rasHewanVar,jenisHewanVar,jumlahHewanVarPar,jadwalMakanVar,kandangIdTerpilih,obatIdTerpilih);
                database.daoHewan().insertDataHewan(row);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("nama_hewan", namaHewanVar);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        infoMsg.setNegativeButton("Perbarui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                namaHewan.requestFocus();
                dialogInterface.dismiss();
            }
        });
        infoMsg.show();
    }

    public void updateData(int id_hewan){
        AlertDialog.Builder infoMsg = new AlertDialog.Builder(TambahHewanActivity.this);
        infoMsg.setTitle("Apakah Anda Yakin?");
        infoMsg.setMessage("Apakah anda yakin ingin memperbarui data sebagai berikut?\n\n"+"" +
                "Nama Hewan: " + namaHewan.getText().toString() +"\n"+
                "Ras Hewan: " + rasHewan.getText().toString() +"\n"+
                "Jumlah Hewan: " + jumlahHewan.getText().toString()+"\n"+
                "Jenis Hewan: " + spinnerJenisHewan.getSelectedItem().toString() +"\n"+
                "Jadwal Makan: " +spinnerJadwalMakan.getSelectedItem().toString() +"\n"+
                "Tempat Hewan: " + spinnerPilihKandang.getSelectedItem().toString() +"\n"+
                "Obat Hewan: " + spinnerPilihObat.getSelectedItem().toString() +"\n");
        infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String namaHewanVar = namaHewan.getText().toString();
                String rasHewanVar = rasHewan.getText().toString();
                String jumlahHewanVar = jumlahHewan.getText().toString();
                int jumlahHewanVarPar = Integer.parseInt(jumlahHewanVar);
                String jenisHewanVar = spinnerJenisHewan.getSelectedItem().toString();
                String jadwalMakanVar = spinnerJadwalMakan.getSelectedItem().toString();
                String namaKandangTerpilih = spinnerPilihKandang.getSelectedItem().toString();
                int kandangIdTerpilih = database.daoHewan().getKandangIdByNama(namaKandangTerpilih);
                String namaObatTerpilih = spinnerPilihObat.getSelectedItem().toString();
                int obatIdTerpilih = database.daoHewan().getObatIdByNama(namaObatTerpilih);
                TabelHewan row = new TabelHewan(namaHewanVar,rasHewanVar,jenisHewanVar,jumlahHewanVarPar,jadwalMakanVar,kandangIdTerpilih,obatIdTerpilih);
                row.setId(id_hewan);
                database.daoHewan().updateHewan(row);
                finish();
            }
        });
        infoMsg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                namaHewan.requestFocus();
                dialogInterface.dismiss();
            }
        });
        infoMsg.show();

    }
}