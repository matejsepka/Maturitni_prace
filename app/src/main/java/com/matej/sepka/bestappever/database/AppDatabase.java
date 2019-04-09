package com.matej.sepka.bestappever.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Group.class, GameSituation.class, Player.class, Training.class, Attendance.class, Animation.class}, version = 12, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase getInstance(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public abstract GroupDao getGroupDao();
    public abstract GameSituationDao getGameSituationDao();
    public abstract PlayerDao getPlayerDao();
    public abstract TrainingDao getTrainingDao();
    public abstract AttendanceDao getAttendanceDao();
    public abstract AnimationDao getAnimationDao();
}
