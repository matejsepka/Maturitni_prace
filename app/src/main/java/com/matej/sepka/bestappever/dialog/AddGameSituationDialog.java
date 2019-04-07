package com.matej.sepka.bestappever.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.matej.sepka.bestappever.database.GameSituation;
import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddGameSituationDialog extends BottomSheetDialogFragment {

    private AddDialogListener listener;

    private EditText editGameSituationName;
    private EditText editGameSituationDescription;
    private Switch beginnerSwitch;
    private Switch advancedSwitch;
    private Switch competitiveSwitch;
    private Switch defenseSwitch;
    private Switch attackSwitch;
    private Switch receiveSwitch;
    private Switch serveSwitch;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_game_situation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editGameSituationName = view.findViewById(R.id.edit_game_situation_name);
        editGameSituationDescription = view.findViewById(R.id.edit_game_situation_description);
        beginnerSwitch = view.findViewById(R.id.beginner_switch);
        advancedSwitch = view.findViewById(R.id.advanced_switch);
        competitiveSwitch = view.findViewById(R.id.competitive_switch);
        defenseSwitch = view.findViewById(R.id.defense_switch);
        attackSwitch = view.findViewById(R.id.attack_switch);
        receiveSwitch = view.findViewById(R.id.receive_switch);
        serveSwitch = view.findViewById(R.id.serve_switch);
        Button buttonSave = view.findViewById(R.id.button_save);

        beginnerSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedSwitch.setChecked(false);
                competitiveSwitch.setChecked(false);
            }
        });

        advancedSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginnerSwitch.setChecked(false);
                competitiveSwitch.setChecked(false);
            }
        });

        competitiveSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginnerSwitch.setChecked(false);
                advancedSwitch.setChecked(false);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameSituation gameSituation = new GameSituation();
                gameSituation.setName(editGameSituationName.getText().toString());

                String difficulty;
                if (beginnerSwitch.isChecked()) {
                    difficulty = "začátečníci";
                } else if (advancedSwitch.isChecked()) {
                    difficulty = "pokročilí hráči";
                } else if (competitiveSwitch.isChecked()) {
                    difficulty = "závodní hráči";
                } else {
                    difficulty = "---";
                }
                gameSituation.setDifficulty(difficulty);
                gameSituation.setDescription(editGameSituationDescription.getText().toString());

                if (defenseSwitch.isChecked()) {
                    gameSituation.setDefense(true);
                } else {
                    gameSituation.setDefense(false);
                }

                if (attackSwitch.isChecked()) {
                    gameSituation.setAttack(true);
                } else {
                    gameSituation.setAttack(false);
                }

                if (receiveSwitch.isChecked()) {
                    gameSituation.setReceive(true);
                } else {
                    gameSituation.setReceive(false);
                }

                if (serveSwitch.isChecked()) {
                    gameSituation.setServe(true);
                } else {
                    gameSituation.setServe(false);
                }

                AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
                appDatabase.getGameSituationDao().insert(gameSituation);

                listener.addGameSituation(gameSituation);
                dismiss();
            }
        });
    }
}
