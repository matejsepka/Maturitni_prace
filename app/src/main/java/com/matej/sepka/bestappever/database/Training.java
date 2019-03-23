package com.matej.sepka.bestappever.database;

import java.io.Serializable;
import java.util.Comparator;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Training implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String groupName;
    private long millis;

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

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public static Comparator<Training> TraMilComparator = new Comparator<Training>() {
        @Override
        public int compare(Training o1, Training o2) {
            int TrainingMillis1 = (int) o1.getMillis();
            int TrainingMillis2 = (int) o2.getMillis();

            return TrainingMillis1-TrainingMillis2;
        }
    };
}
