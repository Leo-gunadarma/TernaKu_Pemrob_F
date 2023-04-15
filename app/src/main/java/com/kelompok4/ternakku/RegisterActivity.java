package com.kelompok4.ternakku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextNama, editTextEmail, editTextPassword, editTextAlamat, editTextNoTelp;
    Button tombolRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextNama = findViewById(R.id.editTextNama);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextNoTelp = findViewById(R.id.editTextNoTelp);
        tombolRegister = findViewById(R.id.buttonDaftar);

        tombolRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNama.getText().toString().trim().length()==0){
                    editTextNama.setError("Nama harus diisi !");
                    editTextNama.requestFocus();
                }else if(editTextEmail.getText().toString().trim().length()==0){
                    editTextEmail.setError("Email harus diisi !");
                    editTextEmail.requestFocus();
                }else if(editTextPassword.getText().toString().trim().length()==0){
                    editTextPassword.setError("Password harus diisi !");
                    editTextPassword.requestFocus();
                }else if(editTextAlamat.getText().toString().trim().length()==0){
                    editTextAlamat.setError("Alamat harus diisi !");
                    editTextAlamat.requestFocus();
                }else if(editTextNoTelp.getText().toString().trim().length()==0){
                    editTextNoTelp.setError("No Telp harus diisi !");
                    editTextNoTelp.requestFocus();
                }else {
                    Toast toast = Toast.makeText(RegisterActivity.this, "Register Berhasil, Silahkan Login", Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
            }
        });
    }
}