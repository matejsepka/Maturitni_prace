package com.matej.sepka.bestappever.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GameSituationDao {

    @Query("SELECT * FROM gamesituation")
    List<GameSituation> getAll();

    @Insert
    void insert(GameSituation gameSituation);

    @Delete
    void delete(GameSituation gameSituation);
}
