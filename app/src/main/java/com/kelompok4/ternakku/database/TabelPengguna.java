package com.kelompok4.ternakku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_pengguna")
public class TabelPengguna {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_pengguna")
    String namaPengguna;
    @ColumnInfo (name = "email_pengguna")
    String emailPengguna;
    @ColumnInfo (name = "password_pengguna")
    String passwordPengguna;
    @ColumnInfo (name = "alamat_pengguna")
    String alamatPengguna;
    @ColumnInfo (name = "no_telp_pengguna")
    String noTelpPengguna;
}
