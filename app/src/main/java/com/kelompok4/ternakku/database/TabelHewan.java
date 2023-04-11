package com.kelompok4.ternakku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "tb_hewan")
public class TabelHewan {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_hewan")
    String namaHewan;
    @ColumnInfo (name = "ras_hewan")
    String rasHewan;
    @ColumnInfo (name = "jenis_hewan")
    String jenisHewan;
    @ColumnInfo (name = "jumlah_hewan")
    int jumlahHewan;
    @ColumnInfo (name = "jadwal_makan")
    String jadwalMakan;
}
