package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button tombolLogin;
    DatabaseTernaku database;

    List<TabelPengguna> dataLogin = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SessionManagement session = new SessionManagement(LoginActivity.this);
        database = DatabaseTernaku.getDbInstance(this);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        tombolLogin = findViewById(R.id.buttonLogin);

        tombolLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(email.length() == 0){
                    editTextEmail.setError("Isikan email terlebih dahulu !");
                    editTextEmail.requestFocus();
                }else if(password.length() == 0){
                    editTextEmail.setError("Isikan password terlebih dahulu !");
                    editTextEmail.requestFocus();
                }else{
                    dataLogin = database.daoHewan().loginPengguna(email, password);
                    int isLogin =  dataLogin.size();
                    if(isLogin <= 0){
                        Toast toast = Toast.makeText(LoginActivity.this, "Gagal Login, Username atau Password Salah", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        session.saveSession(dataLogin.get(0).getId(), dataLogin.get(0).getNamaPengguna());
                        Toast toast = Toast.makeText(LoginActivity.this, "Berhasil Login, Hai "+dataLogin.get(0).getNamaPengguna(), Toast.LENGTH_LONG);
                        toast.show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement session = new SessionManagement(this);
        if(session.statusLogin()){
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}