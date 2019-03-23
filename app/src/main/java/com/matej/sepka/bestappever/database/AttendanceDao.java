package com.matej.sepka.bestappever.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AttendanceDao {

    @Query("SELECT * FROM attendance")
    List<Attendance> getAll();

    @Insert
    void insert(Attendance attendance);

    @Delete
    void delete(Attendance attendance);
}
