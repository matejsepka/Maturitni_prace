package com.matej.sepka.appPackage.dialog;

import com.matej.sepka.appPackage.database.Player;

public interface PlayerDialogListener {
    void addPlayer(Player player);
    void deletePlayer(Player player);
}
