package com.matej.sepka.bestappever.fragment;

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

import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.activity.GroupDetailActivity;
import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.AppDatabase;

import java.util.List;

public class GroupsFragment extends Fragment {
    private GroupsAdapter groupsAdapter;

    @Override
    public void onResume() {
        super.onResume();
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
        List<Group> listGroups = appDatabase.getGroupDao().getAll();
        groupsAdapter.updateList(listGroups);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_groups, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.list_trainings);
        AppDatabase appDatabase = AppDatabase.getInstance(getActivity().getApplication());
        List<Group> listGroups = appDatabase.getGroupDao().getAll();

        groupsAdapter = new GroupsAdapter(listGroups);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(groupsAdapter);
    }

    public void addGroup(Group group) {
        groupsAdapter.addItem(group);
    }

    private class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder> {
        private List<Group> listGroups;

        GroupsAdapter(List<Group> listGroups) {
            this.listGroups = listGroups;
        }

        void addItem(Group group) {
            listGroups.add(group);
            notifyDataSetChanged();
        }

        void updateList(List<Group> listGroups) {
            this.listGroups = listGroups;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_group, parent, false);
            return new GroupsViewHolder(view);
        }

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

        @Override
        public int getItemCount() {
            return listGroups.size();
        }

        class GroupsViewHolder extends RecyclerView.ViewHolder {
            TextView textName;

            GroupsViewHolder(@NonNull View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.text_group_name);
            }
        }
    }
}
