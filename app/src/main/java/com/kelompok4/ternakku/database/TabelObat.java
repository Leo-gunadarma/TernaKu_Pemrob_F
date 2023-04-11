package com.kelompok4.ternakku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_obat")
public class TabelObat {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_obat")
    String namaObat;
    @ColumnInfo (name = "jumlah_obat")
    int jumlahObat;
    @ColumnInfo (name = "deskripsi_obat")
    String deskripsiObat;
    @ColumnInfo (name = "peraturan")
    String peraturan;
}
