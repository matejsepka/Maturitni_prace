package com.matej.sepka.bestappever.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.Player;
import com.matej.sepka.bestappever.dialog.AddPlayerDialog;
import com.matej.sepka.bestappever.dialog.PlayerDialogListener;
import com.matej.sepka.bestappever.dialog.DeleteGroupDialog;
import com.matej.sepka.bestappever.dialog.DeletePlayerDialog;

import java.util.ArrayList;
import java.util.List;

public class GroupDetailActivity extends AppCompatActivity implements PlayerDialogListener {

    private Group group;
    private PlayersAdapter playersAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        RecyclerView recyclerView = findViewById(R.id.player_recycler_view);
        List<Player> AllPlayersList = appDatabase.getPlayerDao().getall();
        List<Player> listPlayers = new ArrayList<>();
        for (int i = 0; i < AllPlayersList.size(); i++) {
            Player player = AllPlayersList.get(i);
            if (player.getGroup().equals(group.getName())) {
                listPlayers.add(player);
            }
        }
        playersAdapter = new PlayersAdapter(listPlayers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(playersAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        //Odkazy

        group = (Group) getIntent().getExtras().getSerializable("group");
        setTitle(group.getName());
        Button attendanceBtn = findViewById(R.id.attendance_btn);
        RecyclerView recyclerView = findViewById(R.id.player_recycler_view);
        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());

        //Vybrání pouze těch hráčů, kteří náleží do vybrané skupiny

        List<Player> AllPlayersList = appDatabase.getPlayerDao().getall();
        List<Player> listPlayers = new ArrayList<>();
        for (int i = 0; i < AllPlayersList.size(); i++) {
            Player player = AllPlayersList.get(i);
            if (player.getGroup().equals(group.getName())) {
                listPlayers.add(player);
            }
        }

        //Spuštění adaptéru pro vyplnění ryclerview

        playersAdapter = new PlayersAdapter(listPlayers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(playersAdapter);

        //Otevření stránky s docházkou

        attendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AttendanceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("group", group);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //Implementace Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_group_detail, menu);
        return true;
    }

    //Menu OnClickListener

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showDeleteDialog();
                break;
            case R.id.action_add_player:
                showAddPlayerDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Dialog pro odstranění skupiny

    private void showDeleteDialog() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("group", group);
        DeleteGroupDialog deleteGroupDialog = new DeleteGroupDialog();
        deleteGroupDialog.setArguments(bundle);
        deleteGroupDialog.show(getSupportFragmentManager(), "delete_group_dialog");
    }

    //Dialog pro přidání hráče

    private void showAddPlayerDialog() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("group", group);
        AddPlayerDialog addPlayerDialog = new AddPlayerDialog();
        addPlayerDialog.setArguments(bundle);
        addPlayerDialog.show(getSupportFragmentManager(), "add_player_dialog");
    }

    //Odkaz na update po přidání hráče

    @Override
    public void addPlayer(Player player) {
        playersAdapter.addItem(player);
    }

    //Odkaz na update po odstranění hráče

    @Override
    public void deletePlayer(Player player) {
        playersAdapter.deleteItem(player);
    }

    //RecyclerView Adapter

    private class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
        private List<Player> listPlayers;

        PlayersAdapter (List<Player> listPlayers) {
            this.listPlayers = listPlayers;
        }

        //Update po přidání hráče

        void addItem(Player player) {
            listPlayers.add(player);
            notifyDataSetChanged();
        }

        //Upddate po odstranění hráče

        void deleteItem(Player player) {
            listPlayers.remove(player);
            notifyDataSetChanged();
        }


        //Začátek celého procesu (odkaz na layout itemů)

        @NonNull
        @Override
        public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
            PlayersViewHolder playersViewHolder = new PlayersViewHolder(view);
            return playersViewHolder;
        }

        //Opakující se metoda pro přiřazování itemů k polím v RecyclerView

        @Override
        public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
            final Player player = listPlayers.get(position);
            holder.textName.setText(player.getName());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("player", player);
                    DeletePlayerDialog deletePlayerDialog = new DeletePlayerDialog();
                    deletePlayerDialog.setArguments(bundle);
                    deletePlayerDialog.show(getSupportFragmentManager(), "delete_player_dialog");
                    return false;
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PlayerDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("player", player);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        //Počet opakování

        @Override
        public int getItemCount() {
            return listPlayers.size();
        }

        //Samotné vepsání textu do RecyclerView

        class PlayersViewHolder extends RecyclerView.ViewHolder {
            TextView textName;

            PlayersViewHolder(@NonNull View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_player_name);
            }
        }
    }
}
