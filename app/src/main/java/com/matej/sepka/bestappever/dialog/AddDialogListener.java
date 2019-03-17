package com.matej.sepka.bestappever.dialog;

import com.matej.sepka.bestappever.database.GameSituation;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.database.Player;

public interface AddDialogListener {
    void addGroup(Group group);
    void addGameSituation(GameSituation gameSituation);
}
