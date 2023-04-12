package com.kelompok4.ternakku.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TabelHewan.class , TabelKandang.class, TabelObat.class, TabelPengguna.class} ,version = 1)
public abstract class DatabaseTernaku extends RoomDatabase {
    public abstract DaoCRUD daoHewan();
    private static DatabaseTernaku INSTANCE;
    public static DatabaseTernaku getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseTernaku.class,"db_hewan")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
