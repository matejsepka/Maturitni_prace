package com.matej.sepka.appPackage.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.Animation;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.GameSituation;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteGameSituationDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final GameSituation gameSituation = (GameSituation) getArguments().getSerializable("game_situation");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = View.inflate(getContext(), R.layout.dialog_delete_game_situation, null);

        builder.setView(view)
                .setNegativeButton("zrušit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());

                        List<Animation> AllAnimationsList = appDatabase.getAnimationDao().getAll();
                        for (int i = 0; i < AllAnimationsList.size(); i++) {
                            Animation anim = AllAnimationsList.get(i);
                            if (anim.getName().equals(gameSituation.getName())) {
                                appDatabase.getAnimationDao().delete(anim);
                            }
                        }

                        appDatabase.getGameSituationDao().delete(gameSituation);
                        getActivity().finish();
                        dismiss();
                    }
                });
        return builder.create();
    }

}