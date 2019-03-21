package com.matej.sepka.bestappever.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DateOfTrainingDao {

    @Query("SELECT * FROM dateOfTraining")
    List<DateOfTraining> getAll();

    @Insert
    void insert(DateOfTraining dateOfTraining);

    @Delete
    void delete(DateOfTraining dateOfTraining);
}
