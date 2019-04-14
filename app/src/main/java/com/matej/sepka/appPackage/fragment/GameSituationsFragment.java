package com.matej.sepka.appPackage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.activity.GameSituationDetailActivity;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.GameSituation;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GameSituationsFragment extends Fragment {
    private GameSituationsAdapter gameSituationsAdapter;

    //metoda při opětovném spuštění
    @Override
    public void onResume() {
        super.onResume();
        //update seznamu
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
        List<GameSituation> listGameSituations = appDatabase.getGameSituationDao().getAll();
        gameSituationsAdapter.updateList(listGameSituations);
    }

    //layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_situations, container, false);
    }

    //on Create
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //odkazy
        RecyclerView recyclerView = view.findViewById(R.id.list_game_situations);
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
        List<GameSituation> listGameSituations = appDatabase.getGameSituationDao().getAll();

        //adapter
        gameSituationsAdapter = new GameSituationsAdapter(listGameSituations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gameSituationsAdapter);
    }

    //přidání cvičení
    public void addGameSituation(GameSituation gameSituation) {
        gameSituationsAdapter.addItem(gameSituation);
    }

    //třída recyclerview adapteru
    private class GameSituationsAdapter extends RecyclerView.Adapter<GameSituationsAdapter.GameSituationsViewHolder> {
        private List<GameSituation> listGameSituations;

        GameSituationsAdapter(List<GameSituation> listGameSituations) {
            this.listGameSituations = listGameSituations;
        }

        //přidání položky
        void addItem(GameSituation gameSituation) {
            listGameSituations.add(gameSituation);
            notifyDataSetChanged();
        }

        //update seznamu
        void updateList(List<GameSituation> listGameSituations) {
            this.listGameSituations = listGameSituations;
            notifyDataSetChanged();
        }

        //Začátek celého procesu (odkaz na layout itemů)
        @NonNull
        @Override
        public GameSituationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_game_situation, parent, false);
            return new GameSituationsViewHolder(view);
        }

        //Opakující se metoda pro přiřazování itemů k polím v RecyclerView
        @Override
        public void onBindViewHolder(@NonNull GameSituationsViewHolder holder, int position) {
            final GameSituation gameSituation = listGameSituations.get(position);
            holder.textName.setText(gameSituation.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), GameSituationDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("game_situation", gameSituation);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        //Počet opakování
        @Override
        public int getItemCount() {
            return listGameSituations.size();
        }

        //Samotné vepsání textu do RecyclerView
        class GameSituationsViewHolder extends RecyclerView.ViewHolder {
            TextView textName;

            GameSituationsViewHolder(@NonNull View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_game_situation_name);
            }
        }
    }
}
