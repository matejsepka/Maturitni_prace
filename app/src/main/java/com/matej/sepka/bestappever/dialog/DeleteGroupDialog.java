package com.matej.sepka.bestappever.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.database.Player;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteGroupDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Group group = (Group) getArguments().getSerializable("group");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = View.inflate(getContext(), R.layout.dialog_delete_group, null);

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
                        List<Player> AllPlayersList = appDatabase.getPlayerDao().getall();

                        for (int i = 0; i < AllPlayersList.size(); i++) {
                            Player player = AllPlayersList.get(i);
                            String string1 = player.getGroup();
                            String string2 = group.getName();
                            if (string1.equals(string2)) {
                                appDatabase.getPlayerDao().delete(player);
                            }
                        }

                        appDatabase.getGroupDao().delete(group);
                        getActivity().finish();
                        dismiss();
                    }
                });
        return builder.create();
    }

}