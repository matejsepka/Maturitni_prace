package com.matej.sepka.bestappever.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Attendance;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.database.Player;
import com.matej.sepka.bestappever.database.Training;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddPlayerDialog extends BottomSheetDialogFragment {
    private PlayerDialogListener listener;
    private Group group;
    private EditText editPlayerName;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (PlayerDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editPlayerName = view.findViewById(R.id.edit_player_name);
        Button buttonSave = view.findViewById(R.id.button_save);
        group = (Group) getArguments().getSerializable("group");

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = new Player();
                String groupName = group.getName();
                player.setName(editPlayerName.getText().toString());
                player.setGroup(groupName);

                AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
                appDatabase.getPlayerDao().insert(player);

                List<Training> AllTrainingsList = appDatabase.getTrainingDao().getall();
                for (int i = 0; i < AllTrainingsList.size(); i++) {
                    Training training = AllTrainingsList.get(i);
                    if (training.getGroupName().equals(group.getName())) {
                        Attendance attendance = new Attendance();
                        attendance.setGroupName(group.getName());
                        attendance.setPlayerName(player.getName());
                        attendance.setTrainingSeconds(training.getMillis());
                        appDatabase.getAttendanceDao().insert(attendance);
                    }
                }

                listener.addPlayer(player);
                dismiss();
            }
        });
    }
}
