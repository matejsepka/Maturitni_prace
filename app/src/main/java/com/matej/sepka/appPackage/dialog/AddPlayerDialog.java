package com.matej.sepka.appPackage.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.Attendance;
import com.matej.sepka.appPackage.database.Group;
import com.matej.sepka.appPackage.database.Player;
import com.matej.sepka.appPackage.database.Training;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddPlayerDialog extends BottomSheetDialogFragment {
    //implementace proměnných
    private PlayerDialogListener listener;
    private Group group;
    private EditText editPlayerName;

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

    //layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_player, container, false);
    }

    //on Create
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //odkazy
        editPlayerName = view.findViewById(R.id.edit_player_name);
        Button buttonSave = view.findViewById(R.id.button_save);
        group = (Group) getArguments().getSerializable("group");

        //tlačítko pro uložení
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = new Player();
                //jméno hráče
                player.setName(editPlayerName.getText().toString());
                //příslušná skupina
                player.setGroup(group.getName());
                //datum narození
                player.setDateOfBirth(0);

                //uložení do databáze
                AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
                appDatabase.getPlayerDao().insert(player);

                //Vytvoření docházky a její uložení do databáze
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

                //listener odešle data do GroupDetailActivity
                listener.addPlayer(player);
                dismiss();
            }
        });
    }
}
