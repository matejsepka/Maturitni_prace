package com.matej.sepka.appPackage.dialog;

import com.matej.sepka.appPackage.database.GameSituation;
import com.matej.sepka.appPackage.database.Group;

//listener pro dialogy vytvářející skupinu a cvičení
public interface AddDialogListener {
    void addGroup(Group group);
    void addGameSituation(GameSituation gameSituation);
}
