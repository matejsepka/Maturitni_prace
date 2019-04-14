package com.matej.sepka.appPackage.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.Attendance;
import com.matej.sepka.appPackage.database.Player;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeletePlayerDialog extends AppCompatDialogFragment {
    private PlayerDialogListener listener;

    //listner
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
        //hráč
        final Player player = (Player) getArguments().getSerializable("player");

        //builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //layout
        View view = View.inflate(getContext(), R.layout.dialog_delete_player, null);
        //odkazy
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
                        //smazání hráče a jeho docházky z databáze
                        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
                        appDatabase.getPlayerDao().delete(player);

                        List<Attendance> AllAttendanceList = appDatabase.getAttendanceDao().getAll();
                        for (int i = 0; i < AllAttendanceList.size(); i++) {
                            Attendance attendance = AllAttendanceList.get(i);
                            if ((attendance.getGroupName().equals(player.getGroup())) && (attendance.getPlayerName().equals(player.getName()))) {
                                appDatabase.getAttendanceDao().delete(attendance);
                            }
                        }

                        //listener smaže hráče z GroupDetailActivity
                        listener.deletePlayer(player);
                        dismiss();
                    }
                });
        return builder.create();
    }
}