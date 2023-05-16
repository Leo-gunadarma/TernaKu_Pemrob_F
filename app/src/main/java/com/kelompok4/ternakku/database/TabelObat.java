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

//    Membuat constructor
    public TabelObat(String namaObat, int jumlahObat, String deskripsiObat, String peraturan) {
        this.namaObat = namaObat;
        this.jumlahObat = jumlahObat;
        this.deskripsiObat = deskripsiObat;
        this.peraturan = peraturan;
    }
//    Membuat Setter
    public void setId(int id) {
        this.id = id;
    }
//    Membuat getter
    public int getId() {
        return id;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public int getJumlahObat() {
        return jumlahObat;
    }

    public String getDeskripsiObat() {
        return deskripsiObat;
    }

    public String getPeraturan() {
        return peraturan;
    }
}
