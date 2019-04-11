package com.matej.sepka.appPackage.database;

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
    private boolean defense;
    private boolean attack;
    private boolean receive;
    private boolean serve;

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

    public void setDefense(boolean defense) {
        this.defense = defense;
    }

    public boolean isDefense() {
        return defense;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public boolean isAttack() {
        return attack;
    }

    public void setReceive(boolean receive) {
        this.receive = receive;
    }

    public boolean isReceive() {
        return receive;
    }

    public void setServe(boolean serve) {
        this.serve = serve;
    }

    public boolean isServe() {
        return serve;
    }
}
