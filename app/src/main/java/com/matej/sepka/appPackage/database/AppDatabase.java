package com.matej.sepka.appPackage.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//databáze s pod ní spadajícími class soubory
@Database(entities = {Group.class, GameSituation.class, Player.class, Training.class, Attendance.class, Animation.class}, version = 12, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase getInstance(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration() //při úpravy databáze budou smazána veškerá data v ní uložená
                .allowMainThreadQueries()
                .build();
    }

    //metody pro volání dao interfacu
    public abstract GroupDao getGroupDao();
    public abstract GameSituationDao getGameSituationDao();
    public abstract PlayerDao getPlayerDao();
    public abstract TrainingDao getTrainingDao();
    public abstract AttendanceDao getAttendanceDao();
    public abstract AnimationDao getAnimationDao();
}
