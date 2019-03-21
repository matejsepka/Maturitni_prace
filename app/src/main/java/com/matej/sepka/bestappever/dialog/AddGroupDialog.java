package com.matej.sepka.bestappever.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.matej.sepka.bestappever.database.DateOfTraining;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static java.util.Calendar.YEAR;

public class AddGroupDialog extends BottomSheetDialogFragment {
    private AddDialogListener listener;
    private final long millisInDay = 86400000;
    private EditText editGroupName;

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
        return inflater.inflate(R.layout.dialog_add_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editGroupName = view.findViewById(R.id.edit_group_name);
        Button buttonSave = view.findViewById(R.id.button_save);

        final Switch mondaySwitch = view.findViewById(R.id.monday_switch);
        Switch tuesdaySwitch = view.findViewById(R.id.tuesday_switch);
        Switch wednesdaySwitch = view.findViewById(R.id.wednesday_switch);
        Switch thursdaySwitch = view.findViewById(R.id.thursday_switch);
        Switch fridaySwitch = view.findViewById(R.id.friday_switch);
        Switch saturdaySwitch = view.findViewById(R.id.saturday_switch);
        Switch sundaySwitch = view.findViewById(R.id.sunday_switch);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Vložení skupiny
                Group group = new Group();
                group.setName(editGroupName.getText().toString());

                AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
                appDatabase.getGroupDao().insert(group);

                listener.addGroup(group);

                //Vložení dat

                Date today = new Date();
                today.setHours(0);
                today.setMinutes(0);
                today.setSeconds(0);
                long todayMillis = (today.getTime() / 1000) * 1000;
                long futureMillis = todayMillis + (31536000);
                long todayDays = todayMillis / millisInDay;

                if (mondaySwitch.isChecked()) {
                    long helper1 = todayMillis;
                    long helper2 = futureMillis;
                    long helper3 = todayDays;

                    for (long i = helper3; i % 6 != 0; i = i + millisInDay) {
                        helper1 = helper1 + millisInDay;
                    }

                    while (helper1 < helper2) {
                        DateOfTraining dateOfTraining = new DateOfTraining();
                        dateOfTraining.setGroup(group.getName());
                        dateOfTraining.setMillis(helper1);

                        appDatabase.getDateOfTriningDao().insert(dateOfTraining);
                        helper1 = helper1 + (7 * millisInDay);
                    }

                }


                dismiss();
            }
        });
    }
}
