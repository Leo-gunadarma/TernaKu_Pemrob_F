package com.kelompok4.ternakku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelObat;

public class TambahObatActivity extends AppCompatActivity {
    //    Menambahkan object edit text,database dan button
    EditText namaObat, jumlahObat, deskripsiObat,aturanObat;
    Button simpanObat;
    TextView judulForm;
    private DatabaseTernaku database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_obat);
//        Registrasi Object
        namaObat = findViewById(R.id.editTextNamaObat);
        jumlahObat = findViewById(R.id.editTextJumlahObat);
        deskripsiObat = findViewById(R.id.editTextDeskripsiObat);
        aturanObat= findViewById(R.id.editTextAturanObat);
        judulForm = findViewById(R.id.textViewJudulFormObat);
        simpanObat = findViewById(R.id.buttonSimpanObat);
        database = DatabaseTernaku.getDbInstance(this.getApplicationContext());


        if(getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            int id_obat = Integer.parseInt(hasil.getStringExtra("id_obat"));
            namaObat.setText(hasil.getStringExtra("nama_obat"));
            jumlahObat.setText(hasil.getStringExtra("jumlah_obat"));
            deskripsiObat.setText(hasil.getStringExtra("deskripsi_obat"));
            aturanObat.setText(hasil.getStringExtra("aturan_obat"));
            judulForm.setText("Form Perbarui Obat");
            simpanObat.setText("Perbarui Data");
            simpanObat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //        Validasi Input
                    if(namaObat.getText().toString().trim().length()==0){
                        namaObat.setError("Nama Obat harus di isi!");
                        namaObat.requestFocus();
                    }else if(jumlahObat.getText().toString().trim().length()==0){
                        jumlahObat.setError("Jumlah Obat Harus di isi!");
                        jumlahObat.requestFocus();
                    }else if(deskripsiObat.getText().toString().trim().length()==0){
                        deskripsiObat.setError("Deskripsi Obat Harus Di isi!");
                        deskripsiObat.requestFocus();
                    }else if(aturanObat.getText().toString().trim().length()==0){
                        aturanObat.setError("Aturan Obat Harus Di isi!");
                        aturanObat.requestFocus();
                    }else {
                        updateData(id_obat);
                    }
                }
            });
        }else{
            simpanObat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //        Validasi Input
                    if(namaObat.getText().toString().trim().length()==0){
                        namaObat.setError("Nama Obat harus di isi!");
                        namaObat.requestFocus();
                    }else if(jumlahObat.getText().toString().trim().length()==0){
                        jumlahObat.setError("Jumlah Obat Harus di isi!");
                        jumlahObat.requestFocus();
                    }else if(deskripsiObat.getText().toString().trim().length()==0){
                        deskripsiObat.setError("Deskripsi Obat Harus Di isi!");
                        deskripsiObat.requestFocus();
                    }else if(aturanObat.getText().toString().trim().length()==0){
                        aturanObat.setError("Aturan Obat Harus Di isi!");
                        aturanObat.requestFocus();
                    }else {
                        simpanData();
                    }
                }
            });
        }
    }

    public void  simpanData(){
        AlertDialog.Builder infoMsg = new AlertDialog.Builder(TambahObatActivity.this);
        infoMsg.setTitle("Apakah Anda Yakin?");
        infoMsg.setMessage("Apakah anda yakin ingin menginputkan data sebagai berikut?\n\n"+"" +
                "Nama Obat: " + namaObat.getText().toString() +"\n"+
                "Jumlah Obat: " + jumlahObat.getText().toString() +"\n"+
                "Aturan Obat: " + aturanObat.getText().toString() + "\n"+
                "Deskripsi Obat: " + deskripsiObat.getText().toString() + "\n");
        infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String namaObatVar = namaObat.getText().toString();
                String aturanObatVar = aturanObat.getText().toString();
                String deskripsiObatVar = deskripsiObat.getText().toString();
                String jumlahObatVar = jumlahObat.getText().toString();
                int jumlahObatVarPar = Integer.parseInt(jumlahObatVar);
                TabelObat row = new TabelObat(namaObatVar,jumlahObatVarPar,deskripsiObatVar,aturanObatVar);
                database.daoHewan().insertDataObat(row);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("nama_obat", namaObatVar);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        infoMsg.setNegativeButton("Perbarui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                namaObat.requestFocus();
                dialogInterface.dismiss();
            }
        });
        infoMsg.show();
    }

    public void updateData(int id_obat){
        AlertDialog.Builder infoMsg = new AlertDialog.Builder(TambahObatActivity.this);
        infoMsg.setTitle("Apakah Anda Yakin?");
        infoMsg.setMessage("Apakah anda yakin ingin menginputkan data sebagai berikut?\n\n"+"" +
                "Nama Obat: " + namaObat.getText().toString() +"\n"+
                "Jumlah Obat: " + jumlahObat.getText().toString() +"\n"+
                "Aturan Obat: " + aturanObat.getText().toString() + "\n"+
                "Deskripsi Obat: " + deskripsiObat.getText().toString() + "\n");
        infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String namaObatVar = namaObat.getText().toString();
                String aturanObatVar = aturanObat.getText().toString();
                String deskripsiObatVar = deskripsiObat.getText().toString();
                String jumlahObatVar = jumlahObat.getText().toString();
                int jumlahObatVarPar = Integer.parseInt(jumlahObatVar);
                TabelObat row = new TabelObat(namaObatVar,jumlahObatVarPar,deskripsiObatVar,aturanObatVar);
                row.setId(id_obat);
                database.daoHewan().updateObat(row);
                finish();
            }
        });
        infoMsg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                namaObat.requestFocus();
                dialogInterface.dismiss();
            }
        });
        infoMsg.show();
    }
}