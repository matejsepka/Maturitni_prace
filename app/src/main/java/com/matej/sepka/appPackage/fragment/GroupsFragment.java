package com.matej.sepka.appPackage.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matej.sepka.appPackage.database.Group;
import com.matej.sepka.appPackage.activity.GroupDetailActivity;
import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.AppDatabase;

import java.util.List;

public class GroupsFragment extends Fragment {
    private GroupsAdapter groupsAdapter;

    //metoda při opětovném spuštění
    @Override
    public void onResume() {
        super.onResume();
        //update seznamu
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
        List<Group> listGroups = appDatabase.getGroupDao().getAll();
        groupsAdapter.updateList(listGroups);
    }

    //layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_groups, container, false);
    }

    //on Create
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //odkazy
        RecyclerView recyclerView = view.findViewById(R.id.list_trainings);
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
        List<Group> listGroups = appDatabase.getGroupDao().getAll();

        //adapter
        groupsAdapter = new GroupsAdapter(listGroups);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(groupsAdapter);
    }

    //přidání skupiny
    public void addGroup(Group group) {
        groupsAdapter.addItem(group);
    }

    //třída recyclerview adapteru
    private class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder> {
        private List<Group> listGroups;

        GroupsAdapter(List<Group> listGroups) {
            this.listGroups = listGroups;
        }

        //přidání položky
        void addItem(Group group) {
            listGroups.add(group);
            notifyDataSetChanged();
        }

        //update seznamu
        void updateList(List<Group> listGroups) {
            this.listGroups = listGroups;
            notifyDataSetChanged();
        }

        //Začátek celého procesu (odkaz na layout itemů)
        @NonNull
        @Override
        public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_group, parent, false);
            return new GroupsViewHolder(view);
        }

        //Opakující se metoda pro přiřazování itemů k polím v RecyclerView
        @Override
        public void onBindViewHolder(@NonNull GroupsViewHolder holder, int position) {
            final Group group = listGroups.get(position);
            holder.textName.setText(group.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), GroupDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("group", group);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        //Počet opakování
        @Override
        public int getItemCount() {
            return listGroups.size();
        }

        //Samotné vepsání textu do RecyclerView
        class GroupsViewHolder extends RecyclerView.ViewHolder {
            TextView textName;

            GroupsViewHolder(@NonNull View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_group_name);
            }
        }
    }
}
