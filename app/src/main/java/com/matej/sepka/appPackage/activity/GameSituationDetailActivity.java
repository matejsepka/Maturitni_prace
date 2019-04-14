package com.matej.sepka.appPackage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.Animation;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.GameSituation;
import com.matej.sepka.appPackage.dialog.AboutDialogGameSituationDetailActivity;
import com.matej.sepka.appPackage.dialog.DeleteGameSituationDialog;

import java.util.List;

public class GameSituationDetailActivity extends AppCompatActivity {
    //implementace proměnných
    private GameSituation gameSituation;
    private Button animationBtn;

    //metoda po opětovném vstupu na stránku
    @Override
    protected void onResume() {
        super.onResume();
        //for cyklus pro vyhledání animace ke cvičení
        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        List<Animation> AllAnimationsList = appDatabase.getAnimationDao().getAll();
        for (int i = 0; i < AllAnimationsList.size(); i++) {
            Animation anim = AllAnimationsList.get(i);
            if (anim.getName().equals(gameSituation.getName())) {
                animationBtn.setText("Zhlédnout animaci");
            }
        }
    }

    //mtoda onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_situation_detail);
        //cvičení
        gameSituation = (GameSituation) getIntent().getExtras().getSerializable("game_situation");
        //název stránky
        setTitle(gameSituation.getName());
        //databáze
        final AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        //tlačítko pto otevření stránky s animací
        animationBtn = findViewById(R.id.animation_btn);
        //jednotlivá pole pro popis cvičení
        TextView textDifficulty = findViewById(R.id.text_difficulty);
        TextView textCoverage = findViewById(R.id.text_coverage);
        TextView textDescription = findViewById(R.id.text_description);
        //pomocné proměnné pro zaměření cvičení
        String stringCoverage = "---";
        boolean stringChanged = false;

        //obtížnost cvičení
        textDifficulty.setText(gameSituation.getDifficulty());

        //nastavení zaměření cvičení
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
        //popisek cvičení
        textDescription.setText(gameSituation.getDescription());

        //for cyklus pro vyhledání animace ke cvičení
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
                //for cyklus pro vyhledání animace ke cvičení
                boolean gameSituationHasAnimation = false;
                List<Animation> AllAnimationsList = appDatabase.getAnimationDao().getAll();
                for (int i = 0; i < AllAnimationsList.size(); i++) {
                    Animation anim = AllAnimationsList.get(i);
                    if (anim.getName().equals(gameSituation.getName())) {
                        gameSituationHasAnimation = true;
                    }
                }

                if (gameSituationHasAnimation) {
                    //odkaz na stránku zhlédnutí animace, v případě že cvičení animaci již má
                    Intent intent = new Intent(getApplicationContext(), AnimationWatchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("game_situation", gameSituation);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    //odkaz na stránku vytvoření animace, v případě že cvičení animaci ještě nemá
                    Intent intent = new Intent(getApplicationContext(), AnimationCreatorActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("game_situation", gameSituation);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    //přiřazení menu ke stránce
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_situation_detail, menu);
        return true;
    }

    //při kliknutí na jednotku v menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showDeleteDialog();
                break;
            case R.id.action_about:
                showAboutDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //metoda pro načtení dialogu pro odstranění cvičení
    private void showDeleteDialog() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("game_situation", gameSituation);
        DeleteGameSituationDialog deleteGameSituationDialog = new DeleteGameSituationDialog();
        deleteGameSituationDialog.setArguments(bundle);
        deleteGameSituationDialog.show(getSupportFragmentManager(), "delete_game_situation_dialog");
    }

    //metoda pro načtení dialogu s nápovědou
    private void showAboutDialog() {
        AboutDialogGameSituationDetailActivity aboutDialog = new AboutDialogGameSituationDetailActivity();
        aboutDialog.show(getSupportFragmentManager(), "dialog_fragment_about");
    }
}
