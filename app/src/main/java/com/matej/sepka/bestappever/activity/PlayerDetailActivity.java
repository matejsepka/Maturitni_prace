package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Attendance;
import com.matej.sepka.bestappever.database.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDetailActivity extends AppCompatActivity {
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        player = (Player) getIntent().getExtras().getSerializable("player");
        setTitle(player.getName());
        TextView textName = findViewById(R.id.text_name);
        TextView textDOB = findViewById(R.id.text_date_of_birth);
        TextView textPresent = findViewById(R.id.text_present);
        TextView textAbsent = findViewById(R.id.text_absent);
        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        int present = 0;
        int absent = 0;
        String training1 = " trenincích";
        String training2 = " trenincích";

        List<Attendance> listAttendance = new ArrayList<>();
        List<Attendance> AllAttendanceList = appDatabase.getAttendanceDao().getAll();
        for (int i = 0; i < AllAttendanceList.size(); i++) {
            Attendance attendance = AllAttendanceList.get(i);
            if ((attendance.getGroupName().equals(player.getGroup())) && (attendance.getPlayerName().equals(player.getName()))) {
                listAttendance.add(attendance);
            }
        }

        for (int i = 0; i < listAttendance.size(); i++) {
            Attendance attendance = listAttendance.get(i);
            if (attendance.getPresent() == null) {
            } else if (attendance.getPresent().equals("yes")) {
                present++;
            } else if (attendance.getPresent().equals("no")) {
                absent++;
            }
        }

        textName.setText(player.getName());
        //textDOB.setText();
        if (present == 1) {
            training1 = " tréninku";
        }
        if (absent == 1) {
            training2 = " tréninku";
        }
        String presentText = String.valueOf(present) + training1;
        String absentText = String.valueOf(absent) + training2;
        textPresent.setText(presentText);
        textAbsent.setText(absentText);

    }
}
