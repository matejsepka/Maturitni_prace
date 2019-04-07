package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.Animation;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.GameSituation;
import com.matej.sepka.bestappever.dialog.DeleteGameSituationDialog;

import java.util.List;

public class GameSituationDetailActivity extends AppCompatActivity {

    private GameSituation gameSituation;
    private Button animationBtn;

    @Override
    protected void onResume() {
        super.onResume();
        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        List<Animation> AllAnimationsList = appDatabase.getAnimationDao().getAll();
        for (int i = 0; i < AllAnimationsList.size(); i++) {
            Animation anim = AllAnimationsList.get(i);
            if (anim.getName().equals(gameSituation.getName())) {
                animationBtn.setText("Zhlédnout animaci");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_situation_detail);
        gameSituation = (GameSituation) getIntent().getExtras().getSerializable("game_situation");
        setTitle(gameSituation.getName());

        final AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        animationBtn = findViewById(R.id.animation_btn);
        TextView textDifficulty = findViewById(R.id.text_difficulty);
        TextView textCoverage = findViewById(R.id.text_coverage);
        TextView textDescription = findViewById(R.id.text_description);
        String stringCoverage = "Nebylo definováno";
        boolean stringChanged = false;

        textDifficulty.setText(gameSituation.getDifficulty());

        if (gameSituation.isDefense()) {
            stringCoverage = "obrana";
            stringChanged = true;
        }

        if (gameSituation.isAttack()) {
            if (stringChanged) {
                stringCoverage = stringCoverage + ", útok";
            } else {
                stringCoverage = "útok";
                stringChanged = true;
            }
        }

        if (gameSituation.isReceive()) {
            if (stringChanged) {
                stringCoverage = stringCoverage + ", příjem";
            } else {
                stringCoverage = "příjem";
                stringChanged = true;
            }
        }

        if (gameSituation.isServe()) {
            if (stringChanged) {
                stringCoverage = stringCoverage + ", podání";
            } else {
                stringCoverage = "podání";
            }
        }

        textCoverage.setText(stringCoverage);
        textDescription.setText(gameSituation.getDescription());

        List<Animation> AllAnimationsList = appDatabase.getAnimationDao().getAll();
        for (int i = 0; i < AllAnimationsList.size(); i++) {
            Animation anim = AllAnimationsList.get(i);
            if (anim.getName().equals(gameSituation.getName())) {
                animationBtn.setText("Zhlédnout animaci");
            }
        }

        animationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean gameSituationHasAnimation = false;
                List<Animation> AllAnimationsList = appDatabase.getAnimationDao().getAll();
                for (int i = 0; i < AllAnimationsList.size(); i++) {
                    Animation anim = AllAnimationsList.get(i);
                    if (anim.getName().equals(gameSituation.getName())) {
                        gameSituationHasAnimation = true;
                    }
                }

                if (gameSituationHasAnimation) {
                    Intent intent = new Intent(getApplicationContext(), AnimationWatchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("game_situation", gameSituation);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), AnimationCreatorActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("game_situation", gameSituation);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_situation_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showDeleteDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteDialog() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("game_situation", gameSituation);
        DeleteGameSituationDialog deleteGameSituationDialog = new DeleteGameSituationDialog();
        deleteGameSituationDialog.setArguments(bundle);
        deleteGameSituationDialog.show(getSupportFragmentManager(), "delete_game_situation_dialog");
    }
}
