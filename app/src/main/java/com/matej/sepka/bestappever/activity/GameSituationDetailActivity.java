package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.GameSituation;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.dialog.DeleteGameSituationDialog;

public class GameSituationDetailActivity extends AppCompatActivity {

    private GameSituation gameSituation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_situation_detail);
        gameSituation = (GameSituation) getIntent().getExtras().getSerializable("game_situation");

        TextView textGameSituationName = findViewById(R.id.text_game_situation_name);
        textGameSituationName.setText(gameSituation.getName());
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
