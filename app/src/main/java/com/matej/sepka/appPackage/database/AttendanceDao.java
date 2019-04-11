package com.matej.sepka.appPackage.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AttendanceDao {

    @Query("SELECT * FROM attendance")
    List<Attendance> getAll();

    @Insert
    void insert(Attendance attendance);

    @Delete
    void delete(Attendance attendance);

    @Update
    void update(Attendance attendance);
}
