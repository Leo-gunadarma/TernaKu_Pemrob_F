package com.kelompok4.ternakku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "tb_hewan",foreignKeys = {
        @ForeignKey(
                entity = TabelKandang.class,
                parentColumns = "id",
                childColumns = "kandang_id"
        ),
        @ForeignKey(
                entity = TabelObat.class,
                parentColumns = "id",
                childColumns = "obat_id"
        )
})
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
    @ColumnInfo (name = "kandang_id")
    int kandangID;

    @ColumnInfo (name = "obat_id")
    int obatID;

    public TabelHewan(String namaHewan, String rasHewan, String jenisHewan, int jumlahHewan, String jadwalMakan, int kandangID, int obatID) {
        this.namaHewan = namaHewan;
        this.rasHewan = rasHewan;
        this.jenisHewan = jenisHewan;
        this.jumlahHewan = jumlahHewan;
        this.jadwalMakan = jadwalMakan;
        this.kandangID = kandangID;
        this.obatID = obatID;
    }

    public int getId() {
        return id;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public String getRasHewan() {
        return rasHewan;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public int getJumlahHewan() {
        return jumlahHewan;
    }

    public String getJadwalMakan() {
        return jadwalMakan;
    }

    public int getKandangID() {
        return kandangID;
    }

    public int getObatID() {
        return obatID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKandangID(int kandangID) {
        this.kandangID = kandangID;
    }

    public void setObatID(int obatID) {
        this.obatID = obatID;
    }
}



