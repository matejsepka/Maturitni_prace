package com.matej.sepka.bestappever.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.matej.sepka.bestappever.database.GameSituation;
import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddGameSituationDialog extends BottomSheetDialogFragment {

    private AddDialogListener listener;

    private EditText editGameSituationName;

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
        Button buttonSave = view.findViewById(R.id.button_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameSituation gameSituation = new GameSituation();
                gameSituation.setName(editGameSituationName.getText().toString());

                AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
                appDatabase.getGameSituationDao().insert(gameSituation);

                listener.addGameSituation(gameSituation);
                dismiss();
            }
        });
    }
}
