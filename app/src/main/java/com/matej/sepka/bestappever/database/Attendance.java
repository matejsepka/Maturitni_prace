package com.matej.sepka.bestappever.database;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Attendance implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String playerName;
    private String groupName;
    private long TrainingSeconds;
    private String present;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getTrainingSeconds() {
        return TrainingSeconds;
    }

    public void setTrainingSeconds(long trainingSeconds) {
        TrainingSeconds = trainingSeconds;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
}
