package com.matej.sepka.bestappever.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM `group`")
    List<Player> getAll();

    @Insert
    void insert(Player player);

    @Delete
    void delete(Player player);
}
