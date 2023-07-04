package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelPengguna;
import com.kelompok4.ternakku.helper.SessionManagement;

import java.util.ArrayList;
import java.util.List;

public class ProfilActivity extends AppCompatActivity {
    TextView tvNama, tvEmail, tvAlamat, tvTelp;
    Button btnEdit, btnChgPass,btnLogout;
    private DatabaseTernaku database;

    List<TabelPengguna> dataPengguna = new ArrayList<>();
    int idPengguna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        tvNama      = findViewById(R.id.textViewNama);
        tvEmail     = findViewById(R.id.textViewEmail);
        tvAlamat    = findViewById(R.id.textViewAlamat);
        tvTelp      = findViewById(R.id.textViewTelp);
        btnEdit     = findViewById(R.id.buttonEdit);
        btnChgPass  = findViewById(R.id.buttonResetPass);
        btnLogout = findViewById(R.id.buttonLogOut);
        SessionManagement session = new SessionManagement(this);
        idPengguna = session.getId();


        showData();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProf = new Intent(ProfilActivity.this, EditProfilActivity.class);
                startActivity(intentProf);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.removeSession();
                Toast toast = Toast.makeText(ProfilActivity.this, "Berhasil Logout", Toast.LENGTH_LONG);
                toast.show();
                finish();
                Intent intentPass = new Intent(ProfilActivity.this, LoRegActivity.class);
                intentPass.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentPass);

            }
        });
        btnChgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPass = new Intent(ProfilActivity.this, EditPassActivity.class);
                startActivity(intentPass);
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

    public void showData(){
        database     = DatabaseTernaku.getDbInstance(this);
        dataPengguna = database.daoHewan().selectPengguna(idPengguna);

        String nama   = dataPengguna.get(0).getNamaPengguna();
        String email  = dataPengguna.get(0).getEmailPengguna();
        String alamat = dataPengguna.get(0).getAlamatPengguna();
        String telp   = dataPengguna.get(0).getNoTelpPengguna();

        tvNama.setText(nama);
        tvEmail.setText(email);
        tvAlamat.setText(alamat);
        tvTelp.setText(telp);
    }
}