package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelPengguna;
import com.kelompok4.ternakku.helper.SessionManagement;

import java.util.ArrayList;
import java.util.List;

public class EditProfilActivity extends AppCompatActivity {

    EditText etNama, etEmail, etAlamat, etTelp;
    Button btnUpdate;
    DatabaseTernaku database;
    List<TabelPengguna> dataPengguna = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        etNama    = findViewById(R.id.editTextNama);
        etEmail   = findViewById(R.id.editTextEmail);
        etAlamat  = findViewById(R.id.editTextAlamat);
        etTelp    = findViewById(R.id.editTextNoTelp);
        btnUpdate = findViewById(R.id.buttonUpdate);

        SessionManagement session = new SessionManagement(this);
        int idPengguna = session.getId();

        database     = DatabaseTernaku.getDbInstance(this);
        dataPengguna = database.daoHewan().selectPengguna(idPengguna);

        String nama   = dataPengguna.get(0).getNamaPengguna();
        String email  = dataPengguna.get(0).getEmailPengguna();
        String alamat = dataPengguna.get(0).getAlamatPengguna();
        String telp   = dataPengguna.get(0).getNoTelpPengguna();

        etNama.setText(nama);
        etEmail.setText(email);
        etAlamat.setText(alamat);
        etTelp.setText(telp);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNama.getText().toString().trim().length() == 0) {
                    etNama.setError("Nama harus di isi!");
                    etNama.requestFocus();
                } else if (etEmail.getText().toString().trim().length() == 0) {
                    etEmail.setError("Email Harus di isi!");
                    etEmail.requestFocus();
                } else if (etAlamat.getText().toString().trim().length() == 0) {
                    etAlamat.setError("Alamat Harus Di isi!");
                    etAlamat.requestFocus();
                } else if (etTelp.getText().toString().trim().length() == 0) {
                    etTelp.setError("No Telpon Harus Di isi!");
                    etTelp.requestFocus();
                }else {
                    String newNama = etNama.getText().toString().trim();
                    String newEmail = etEmail.getText().toString().trim();
                    String newAlamat = etAlamat.getText().toString().trim();
                    String newTelp = etTelp.getText().toString().trim();

                    database.daoHewan().updatePengguna(idPengguna,newNama,newEmail,newAlamat,newTelp);
                    Toast.makeText(EditProfilActivity.this, "Profil Berhasil Diubah!",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}