package com.matej.sepka.appPackage.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TrainingDao {

    @Query("SELECT * FROM training")
    List<Training> getall();

    @Insert
    void insert(Training training);

    @Delete
    void delete(Training training);
}