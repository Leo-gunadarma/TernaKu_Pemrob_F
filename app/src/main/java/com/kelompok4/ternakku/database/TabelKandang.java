package com.kelompok4.ternakku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_kandang")
public class TabelKandang {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_kandang")
    String namaKandang;
    @ColumnInfo (name = "lokasi_kandang")
    String lokasiKandang;
    @ColumnInfo (name = "luas_kandang")
    int luasKandang;
    @ColumnInfo (name = "kapasitas_kandang")
    int kapasitasKandang;
}
