package com.matej.sepka.appPackage.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.Attendance;
import com.matej.sepka.appPackage.database.Training;
import com.matej.sepka.appPackage.dialog.AboutDialogAttendanceDetailActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceDetailActivity extends AppCompatActivity {
    //implementace proměnných
    private Training training;
    private AttendanceAdapter attendanceAdapter;

    //metoda onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_detail);

        //trénink
        training = (Training) getIntent().getExtras().getSerializable("training");
        //recyclerview
        RecyclerView recyclerView = findViewById(R.id.attendance_player_recycler_view);
        //datum tréninku v čitelném formátu nastaveno do názvu stránky
        Date date = new Date();
        date.setTime(training.getMillis() * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("E dd. MMM yyyy");
        setTitle(sdf.format(date));
        //databáze
        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());

        //vyhledání docházky pro daný trénink
        List<Attendance> listAttendance = new ArrayList<>();
        List<Attendance> AllAttendanceList = appDatabase.getAttendanceDao().getAll();
        for (int i = 0; i < AllAttendanceList.size(); i++) {
            Attendance attendance = AllAttendanceList.get(i);
            if ((attendance.getGroupName().equals(training.getGroupName())) && (attendance.getTrainingSeconds() == (training.getMillis()))) {
                listAttendance.add(attendance);
            }
        }

        //adaptér pro vyplnění recyclerview
        attendanceAdapter = new AttendanceAdapter(listAttendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(attendanceAdapter);
    }

    //načtení menu ke stránce
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
        AboutDialogAttendanceDetailActivity aboutDialog = new AboutDialogAttendanceDetailActivity();
        aboutDialog.show(getSupportFragmentManager(), "dialog_fragment_about");
    }

    //třída adaptéru recyclerview
    private class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {
        private List<Attendance> listAttendance;

        AttendanceAdapter (List<Attendance> listAttendance) {
            this.listAttendance = listAttendance;
        }

        //Začátek celého procesu (odkaz na layout itemů)

        @NonNull
        @Override
        public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance, parent, false);
            AttendanceViewHolder attendanceViewHolder = new AttendanceViewHolder(view);
            return attendanceViewHolder;
        }

        //Opakující se metoda pro přiřazování itemů k polím v RecyclerView

        @Override
        public void onBindViewHolder(@NonNull final AttendanceViewHolder holder, int position) {
            final Attendance attendance = listAttendance.get(position);
            //vyjádření barev v integer formátu
            String green = "#4caf50";
            String red = "#F44336";
            String grey = "#303030";
            final int greenAsInt = Color.parseColor(green);
            final int redAsInt = Color.parseColor(red);
            final int greyAsInt = Color.parseColor(grey);

            //nastavení barev k polí podle stavu docházky
            if (attendance.getPresent() == null) {
                holder.itemView.setBackgroundColor(greyAsInt);
            } else if (attendance.getPresent().equals("yes")){
                holder.itemView.setBackgroundColor(greenAsInt);
            } else if (attendance.getPresent().equals("no")) {
                holder.itemView.setBackgroundColor(redAsInt);
            }
            holder.textName.setText(attendance.getPlayerName());
            //změna barvy a stavu docházky při kliknutí na pole
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (attendance.getPresent() == null) {
                        attendance.setPresent("yes");
                        holder.itemView.setBackgroundColor(greenAsInt);
                    } else if (attendance.getPresent().equals("yes")){
                        attendance.setPresent("no");
                        holder.itemView.setBackgroundColor(redAsInt);
                    } else if (attendance.getPresent().equals("no")) {
                        attendance.setPresent(null);
                        holder.itemView.setBackgroundColor(greyAsInt);
                    }
                    AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
                    appDatabase.getAttendanceDao().update(attendance);
                }
            });
        }

        //Počet opakování

        @Override
        public int getItemCount() {
            return listAttendance.size();
        }

        //Samotné vepsání textu do RecyclerView

        class AttendanceViewHolder extends RecyclerView.ViewHolder {
            TextView textName;

            AttendanceViewHolder(@NonNull View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_player_name_attendance);
            }
        }
    }
}
