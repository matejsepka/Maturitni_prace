package com.matej.sepka.bestappever.dialog;

import com.matej.sepka.bestappever.database.Player;

public interface PlayerDialogListener {
    void addPlayer(Player player);
    void deletePlayer(Player player);
}
