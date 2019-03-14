package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.Player;
import com.matej.sepka.bestappever.dialog.DeleteGroupDialog;

import java.util.List;

public class GroupDetailActivity extends AppCompatActivity {

    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        group = (Group) getIntent().getExtras().getSerializable("group");

        Button addPlayerBtn = findViewById(R.id.add_player_btn);
        final EditText addPlayer = findViewById(R.id.add_player_editText);
        TextView textGroupName = findViewById(R.id.text_group_name);
        textGroupName.setText(group.getName());

        addPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = new Player();
                String groupName;
                groupName = group.getName();
                player.setName(addPlayer.getText().toString());
                player.setGroup(groupName);

                AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
                appDatabase.getPlayerDao().insert(player);
            }
        });

        //TODO Zobrazení hráčů v tabulce (pro začítek v seznamu) / Nalézt všechny hráče v databzim kteří mají správný GroupName
        //TODO Vymazání hráče z databáze

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_group_detail, menu);
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
        bundle.putSerializable("group", group);
        DeleteGroupDialog deleteGroupDialog = new DeleteGroupDialog();
        deleteGroupDialog.setArguments(bundle);
        deleteGroupDialog.show(getSupportFragmentManager(), "delete_group_dialog");
    }
}
