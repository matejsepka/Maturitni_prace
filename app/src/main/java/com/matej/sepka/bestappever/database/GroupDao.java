package com.matej.sepka.bestappever.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GroupDao {

    @Query("SELECT * FROM `group`")
    List<Group> getAll();

    @Insert
    void insert(Group group);

    @Delete
    void delete(Group group);
}
