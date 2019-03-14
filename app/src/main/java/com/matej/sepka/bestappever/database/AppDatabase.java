package com.matej.sepka.bestappever.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Group.class, GameSituation.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase getInstance(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "app_database")
                .allowMainThreadQueries()
                .build();
    }

    public abstract GroupDao getGroupDao();
    public abstract GameSituationDao getGameSituationDao();
}
