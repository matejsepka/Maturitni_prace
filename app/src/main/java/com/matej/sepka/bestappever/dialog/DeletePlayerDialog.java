package com.matej.sepka.bestappever.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Attendance;
import com.matej.sepka.bestappever.database.Player;
import com.matej.sepka.bestappever.database.Training;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeletePlayerDialog extends AppCompatDialogFragment {
    private PlayerDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (PlayerDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Player player = (Player) getArguments().getSerializable("player");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = View.inflate(getContext(), R.layout.dialog_delete_player, null);
        TextView playerNameText = view.findViewById(R.id.text_delete_player);
        playerNameText.setText(player.getName());

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
                        appDatabase.getPlayerDao().delete(player);

                        List<Attendance> AllAttendanceList = appDatabase.getAttendanceDao().getAll();
                        for (int i = 0; i < AllAttendanceList.size(); i++) {
                            Attendance attendance = AllAttendanceList.get(i);
                            if ((attendance.getGroupName().equals(player.getGroup())) && (attendance.getPlayerName().equals(player.getName()))) {
                                appDatabase.getAttendanceDao().delete(attendance);
                            }
                        }

                        listener.deletePlayer(player);
                        dismiss();
                    }
                });
        return builder.create();
    }
}