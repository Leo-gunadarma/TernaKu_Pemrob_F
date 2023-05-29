package com.kelompok4.ternakku.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoCRUD {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDataHewan (TabelHewan ... tabelHewans);

    @Update
    void  updateHewan(TabelHewan tabelHewan);

    @Delete
    void deleteHewan(TabelHewan tabelHewan);

    @Query("SELECT * FROM tb_hewan")
    List<TabelHewan> getAllDataHewan();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertDataKandang (TabelKandang ... tabelKandangs);

    @Update
    void  updateKandang (TabelKandang tabelKandang);

    @Delete
    void deleteKandang(TabelKandang tabelKandang);

    @Query("SELECT * FROM tb_kandang ")
    List<TabelKandang> getAllDataKandang();

    @Query("SELECT nama_kandang FROM tb_kandang ")
    List<String> getAllNamaKandang();

    @Query("SELECT id FROM tb_kandang WHERE nama_kandang = :namaKandang")
    int getKandangIdByNama(String namaKandang);

    @Query("SELECT id FROM tb_obat WHERE nama_obat = :namaObat")
    int getObatIdByNama(String namaObat);

    @Query("SELECT nama_obat FROM tb_obat WHERE id = :obatId")
    String getNamaObatById(int obatId);

    @Query("SELECT nama_kandang FROM tb_kandang WHERE id = :kandangId")
    String getNamaKandangById(int kandangId);

//    Melakukan pengecekan apakah id kandang ada di tabel hewan
    @Query("SELECT COUNT(*) FROM tb_hewan WHERE kandang_id = :kandangId")
    int getHewanCountInKandang(int kandangId);

    @Query("SELECT COUNT(*) FROM tb_hewan WHERE obat_id = :obat_id")
    int getHewanCountInObat(int obat_id);


    // Melakukan pengecekan jumlah data dalam tabel obat
    @Query("SELECT COUNT(*) FROM tb_obat")
    int getObatCount();


    // Melakukan pengecekan jumlah data dalam tabel kandang
    @Query("SELECT COUNT(*) FROM tb_kandang")
    int getKandangCount();

    @Query("SELECT nama_obat FROM tb_obat ")
    List<String> getAllNamaObat();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertDataObat (TabelObat ... tabelObats);

    @Update
    void updateObat (TabelObat tabelObat);

    @Delete
    void deleteObat(TabelObat tabelObat);

    @Query("SELECT * FROM tb_obat ")
    List<TabelObat> getAllDataObat();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertDataPengguna (TabelPengguna ... tabelPenggunas);

    @Query("UPDATE tb_pengguna SET nama_pengguna=:nama, email_pengguna=:email, alamat_pengguna=:alamat, no_telp_pengguna=:telp WHERE id=:id")
    void updatePengguna(int id, String nama, String email, String alamat, String telp);

    @Query("UPDATE tb_pengguna SET password_pengguna=:pass WHERE id=:id")
    void updatePass(int id, String pass);

    @Delete
    void deletePengguna(TabelPengguna tabelPengguna);

    @Query("SELECT * FROM tb_pengguna")
    List<TabelPengguna> getAllDataPengguna();

    @Query("SELECT * FROM tb_pengguna WHERE email_pengguna=:email AND password_pengguna=:password")
    List<TabelPengguna> loginPengguna(String email, String password);

    @Query("SELECT * FROM tb_pengguna WHERE id=:id")
    List<TabelPengguna> selectPengguna(int id);
}
