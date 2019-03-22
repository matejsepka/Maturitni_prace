package com.matej.sepka.bestappever.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.matej.sepka.bestappever.activity.MainActivity;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Training;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddGroupDialog extends BottomSheetDialogFragment {
    private AddDialogListener listener;
    private final long secondsInDay = 86400;
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
        final Switch tuesdaySwitch = view.findViewById(R.id.tuesday_switch);
        final Switch wednesdaySwitch = view.findViewById(R.id.wednesday_switch);
        final Switch thursdaySwitch = view.findViewById(R.id.thursday_switch);
        final Switch fridaySwitch = view.findViewById(R.id.friday_switch);
        final Switch saturdaySwitch = view.findViewById(R.id.saturday_switch);
        final Switch sundaySwitch = view.findViewById(R.id.sunday_switch);

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
                long todaySeconds = today.getTime() / 1000;
                long inOneYearSeconds = todaySeconds + (31536000);
                long todayDays = (todaySeconds + 3600) / secondsInDay;

                if (mondaySwitch.isChecked()) {
                    long helper = todaySeconds;

                    for (long i = todayDays; 4 != i%7; i = i + secondsInDay) {
                        helper = helper + secondsInDay;
                    }

                    while (helper < inOneYearSeconds) {
                        Training training = new Training();
                        training.setGroupName(group.getName());
                        training.setMillis(helper);

                        appDatabase.getTrainingDao().insert(training);
                        helper = helper + (7 * secondsInDay);
                    }
                }

                if (tuesdaySwitch.isChecked()) {
                    long helper = todaySeconds;

                    for (long i = todayDays; 5 != i%7; i = i + secondsInDay) {
                        helper = helper + secondsInDay;
                    }

                    while (helper < inOneYearSeconds) {
                        Training training = new Training();
                        training.setGroupName(group.getName());
                        training.setMillis(helper);

                        appDatabase.getTrainingDao().insert(training);
                        helper = helper + (7 * secondsInDay);
                    }
                }

                if (wednesdaySwitch.isChecked()) {
                    long helper = todaySeconds;

                    for (long i = todayDays; 6 != i%7; i = i + secondsInDay) {
                        helper = helper + secondsInDay;
                    }

                    while (helper < inOneYearSeconds) {
                        Training training = new Training();
                        training.setGroupName(group.getName());
                        training.setMillis(helper);

                        appDatabase.getTrainingDao().insert(training);
                        helper = helper + (7 * secondsInDay);
                    }
                }

                if (thursdaySwitch.isChecked()) {
                    long helper = todaySeconds;

                    for (long i = todayDays; 0 != i%7; i = i + secondsInDay) {
                        helper = helper + secondsInDay;
                    }

                    while (helper < inOneYearSeconds) {
                        Training training = new Training();
                        training.setGroupName(group.getName());
                        training.setMillis(helper);

                        appDatabase.getTrainingDao().insert(training);
                        helper = helper + (7 * secondsInDay);
                    }
                }

                if (fridaySwitch.isChecked()) {
                    long helper = todaySeconds;

                    for (long i = todayDays; 1 != i%7; i = i + secondsInDay) {
                        helper = helper + secondsInDay;
                    }

                    while (helper < inOneYearSeconds) {
                        Training training = new Training();
                        training.setGroupName(group.getName());
                        training.setMillis(helper);

                        appDatabase.getTrainingDao().insert(training);
                        helper = helper + (7 * secondsInDay);
                    }
                }

                if (saturdaySwitch.isChecked()) {
                    long helper = todaySeconds;

                    for (long i = todayDays; 2 != i%7; i = i + secondsInDay) {
                        helper = helper + secondsInDay;
                    }

                    while (helper < inOneYearSeconds) {
                        Training training = new Training();
                        training.setGroupName(group.getName());
                        training.setMillis(helper);

                        appDatabase.getTrainingDao().insert(training);
                        helper = helper + (7 * secondsInDay);
                    }
                }

                if (sundaySwitch.isChecked()) {
                    long helper = todaySeconds;

                    for (long i = todayDays; 3 != i%7; i = i + secondsInDay) {
                        helper = helper + secondsInDay;
                    }

                    while (helper < inOneYearSeconds) {
                        Training training = new Training();
                        training.setGroupName(group.getName());
                        training.setMillis(helper);

                        appDatabase.getTrainingDao().insert(training);
                        helper = helper + (7 * secondsInDay);
                    }
                }
                dismiss();
            }
        });
    }
}
