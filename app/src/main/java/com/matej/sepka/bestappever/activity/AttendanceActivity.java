package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.Group;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AttendanceActivity extends AppCompatActivity {
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        group = (Group) getIntent().getExtras().getSerializable("group");
        TextView attendanceTitle = findViewById(R.id.text_attendance_title);
        attendanceTitle.setText("Docházka skupiny " + group.getName());

    }
}
