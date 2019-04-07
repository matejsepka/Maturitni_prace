package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Attendance;
import com.matej.sepka.bestappever.database.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlayerDetailActivity extends AppCompatActivity {
    private Player player;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private TextView textDOB;

    @Override
    protected void onResume() {
        super.onResume();
        if (player.getDateOfBirth() != 0) {
            Date date = new Date();
            date.setTime(player.getDateOfBirth() * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM yyyy");
            textDOB.setText(sdf.format(date));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        player = (Player) getIntent().getExtras().getSerializable("player");
        setTitle(player.getName());
        TextView textName = findViewById(R.id.text_name);
        textDOB = findViewById(R.id.text_date_of_birth);
        TextView textPresent = findViewById(R.id.text_present);
        TextView textAbsent = findViewById(R.id.text_absent);
        final AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
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

        if (player.getDateOfBirth() == 0) {
            textDOB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_WEEK);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(PlayerDetailActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();
                }
            });
        } else {
            Date date = new Date();
            date.setTime(player.getDateOfBirth() * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM yyyy");
            textDOB.setText(sdf.format(date));
        }

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                Date date = new Date();
                date.setTime(0);
                date.setYear(year - 1900);
                date.setMonth(month - 1);
                date.setDate(dayOfMonth);
                player.setDateOfBirth(date.getTime() / 1000);
                appDatabase.getPlayerDao().update(player);
                SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM yyyy");
                textDOB.setText(sdf.format(date));
            }
        };
    }
}
