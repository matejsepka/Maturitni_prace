package com.matej.sepka.bestappever.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.database.Player;
import com.matej.sepka.bestappever.database.Training;
import com.matej.sepka.bestappever.dialog.DeletePlayerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {
    private Group group;
    private TrainingsAdapter trainingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        group = (Group) getIntent().getExtras().getSerializable("group");
        TextView attendanceTitle = findViewById(R.id.text_attendance_title);
        attendanceTitle.setText("Docházka skupiny " + group.getName());
        RecyclerView recyclerView = findViewById(R.id.attendance_recycler_view);
        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());

        List<Training> AllTrainingsList = appDatabase.getTrainingDao().getall();
        List<Training> listTrainings = new ArrayList<>();
        for (int i = 0; i < AllTrainingsList.size(); i++) {
            Training training = AllTrainingsList.get(i);
            String string1 = training.getGroupName();
            String string2 = group.getName();
            if (string1.equals(string2)) {
                listTrainings.add(training);
            }
        }

        //TODO Seznam listTrainings je třeba seřadit

        //Spuštění adaptéru pro vyplnění ryclerview

        trainingsAdapter = new TrainingsAdapter(listTrainings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(trainingsAdapter);

    }

    private class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.TrainingsViewHolder> {
        private List<Training> listTrainings;

        TrainingsAdapter (List<Training> listTrainings) {
            this.listTrainings = listTrainings;
        }

        //Začátek celého procesu (odkaz na layout itemů)

        @NonNull
        @Override
        public TrainingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
            TrainingsViewHolder trainingsViewHolder = new TrainingsViewHolder(view);
            return trainingsViewHolder;
        }

        //Opakující se metoda pro přiřazování itemů k polím v RecyclerView

        @Override
        public void onBindViewHolder(@NonNull TrainingsViewHolder holder, int position) {
            final Training training = listTrainings.get(position);
            String textname = Long.toString(training.getMillis());
            holder.textName.setText(textname);

            //TODO neměli by se tisknout sekundy ale celé datum (ideálně i s příslušným denem v týdnu)

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //TODO za každým datem bude ještě jedna stránka s hráči překliknutelnými na přítomen / nepřítomen

                }
            });
        }

        //Počet opakování

        @Override
        public int getItemCount() {
            return listTrainings.size();
        }

        //Samotné vepsání textu do RecyclerView

        class TrainingsViewHolder extends RecyclerView.ViewHolder {
            TextView textName;

            TrainingsViewHolder(@NonNull View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_date);
            }
        }
    }
}
