package com.matej.sepka.appPackage.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.Attendance;
import com.matej.sepka.appPackage.database.Group;
import com.matej.sepka.appPackage.database.Player;
import com.matej.sepka.appPackage.database.Training;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteGroupDialog extends AppCompatDialogFragment {

    //on Create
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //skupina
        final Group group = (Group) getArguments().getSerializable("group");
        //builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //layout
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
                        //smazání skupiny a do ní patřících hráčů, tréninků a docházky t databáze
                        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
                        List<Player> AllPlayersList = appDatabase.getPlayerDao().getall();
                        List<Training> AllTrainingsList = appDatabase.getTrainingDao().getall();
                        List<Attendance> AllAttendanceList = appDatabase.getAttendanceDao().getAll();

                        for (int i = 0; i < AllPlayersList.size(); i++) {
                            Player player = AllPlayersList.get(i);
                            if (player.getGroup().equals(group.getName())) {
                                appDatabase.getPlayerDao().delete(player);
                            }
                        }

                        for (int i = 0; i < AllTrainingsList.size(); i++) {
                            Training training = AllTrainingsList.get(i);
                            if (training.getGroupName().equals(group.getName())) {
                                appDatabase.getTrainingDao().delete(training);
                            }
                        }

                        for (int i = 0; i < AllAttendanceList.size(); i++) {
                            Attendance attendance = AllAttendanceList.get(i);
                            if (attendance.getGroupName().equals(group.getName())) {
                                appDatabase.getAttendanceDao().delete(attendance);
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