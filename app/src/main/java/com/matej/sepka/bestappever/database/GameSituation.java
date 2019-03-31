package com.matej.sepka.bestappever.database;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GameSituation implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String description;
    private String difficulty; //beginner, advanced, competitive
    private String gameActivity; //defense, attack, block, serve, receive

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(String gameActivity) {
        this.gameActivity = gameActivity;
    }
}
