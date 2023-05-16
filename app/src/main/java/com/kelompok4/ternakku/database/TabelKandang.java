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

    public TabelKandang(String namaKandang, String lokasiKandang, int luasKandang) {
        this.namaKandang = namaKandang;
        this.lokasiKandang = lokasiKandang;
        this.luasKandang = luasKandang;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNamaKandang() {
        return namaKandang;
    }

    public String getLokasiKandang() {
        return lokasiKandang;
    }

    public int getLuasKandang() {
        return luasKandang;
    }

}


