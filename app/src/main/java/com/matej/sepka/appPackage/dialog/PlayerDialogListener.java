package com.matej.sepka.appPackage.dialog;

import com.matej.sepka.appPackage.database.Player;

//listner pro přidvání a odtraňování hráčů
public interface PlayerDialogListener {
    void addPlayer(Player player);
    void deletePlayer(Player player);
}
