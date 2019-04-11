package com.matej.sepka.appPackage.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AnimationDao {

    @Query("SELECT * FROM animation")
    List<Animation> getAll();

    @Insert
    void insert(Animation animation);

    @Delete
    void delete(Animation animation);

    @Update
    void update(Animation animation);
}
