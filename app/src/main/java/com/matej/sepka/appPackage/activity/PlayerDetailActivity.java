package com.matej.sepka.appPackage.activity;

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

import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.Attendance;
import com.matej.sepka.appPackage.database.Player;
import com.matej.sepka.appPackage.dialog.AboutDialogPlayerDetailActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlayerDetailActivity extends AppCompatActivity {
    //implementace proměnných
    private Player player;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private TextView textDOB;

    //metoda po opětovném vstupu na stránku
    @Override
    protected void onResume() {
        super.onResume();
        //datum narození narození
        if (player.getDateOfBirth() != 0) {
            Date date = new Date();
            date.setTime(player.getDateOfBirth() * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM yyyy");
            textDOB.setText(sdf.format(date));
        }
    }

    //onCreate metoda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        //hráč
        player = (Player) getIntent().getExtras().getSerializable("player");
        //název stránky
        setTitle(player.getName());
        //odkazy a pomocné proměnné
        TextView textName = findViewById(R.id.text_name);
        textDOB = findViewById(R.id.text_date_of_birth);
        TextView textPresent = findViewById(R.id.text_present);
        TextView textAbsent = findViewById(R.id.text_absent);
        final AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        int present = 0;
        int absent = 0;
        String training1 = " trenincích";
        String training2 = " trenincích";

        //vyhledání docházky k danému hráči
        List<Attendance> listAttendance = new ArrayList<>();
        List<Attendance> AllAttendanceList = appDatabase.getAttendanceDao().getAll();
        for (int i = 0; i < AllAttendanceList.size(); i++) {
            Attendance attendance = AllAttendanceList.get(i);
            if ((attendance.getGroupName().equals(player.getGroup())) && (attendance.getPlayerName().equals(player.getName()))) {
                listAttendance.add(attendance);
            }
        }

        //počet přítomných a nepřítomných hodin
        for (int i = 0; i < listAttendance.size(); i++) {
            Attendance attendance = listAttendance.get(i);
            if (attendance.getPresent() == null) {
            } else if (attendance.getPresent().equals("yes")) {
                present++;
            } else if (attendance.getPresent().equals("no")) {
                absent++;
            }
        }

        //nasstavení docházky do textview
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
                    //pokud hráč nemá přiřazené datum narození pak umožníme jeho přidání pomocí datePickerDialogu
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
            //pokud datum narození již má pouze ho načteme do textview
            Date date = new Date();
            date.setTime(player.getDateOfBirth() * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM yyyy");
            textDOB.setText(sdf.format(date));
        }


        //Datepicker dialog a jeho přepis do textview
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

    //přiřazení menu ke stránce
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //při kliknutí na jednotku v menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                showAboutDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //metoda pro načtení dialogu s nápovědou
    private void showAboutDialog() {
        AboutDialogPlayerDetailActivity aboutDialog = new AboutDialogPlayerDetailActivity();
        aboutDialog.show(getSupportFragmentManager(), "dialog_fragment_about");
    }
}
