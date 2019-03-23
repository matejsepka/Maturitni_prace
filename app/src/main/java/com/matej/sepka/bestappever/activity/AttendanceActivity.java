package com.matej.sepka.bestappever.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.database.Training;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
            if (training.getGroupName().equals(group.getName())) {
                listTrainings.add(training);
            }
        }

        Collections.sort(listTrainings, Training.TraMilComparator);

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
            Date date = new Date();
            date.setTime(training.getMillis() * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("E dd. MMM yyyy");
            holder.textName.setText(sdf.format(date));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AttendanceDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("training", training);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
