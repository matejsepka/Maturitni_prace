package com.matej.sepka.bestappever.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.matej.sepka.bestappever.dialog.AboutDialogFragment;
import com.matej.sepka.bestappever.dialog.AddDialogListener;
import com.matej.sepka.bestappever.dialog.AddGameSituationDialog;
import com.matej.sepka.bestappever.dialog.AddGroupDialog;
import com.matej.sepka.bestappever.database.GameSituation;
import com.matej.sepka.bestappever.fragment.GameSituationsFragment;
import com.matej.sepka.bestappever.database.Group;
import com.matej.sepka.bestappever.fragment.GroupsFragment;
import com.matej.sepka.bestappever.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements AddDialogListener {
    private static final int PAGE_COUNT = 2;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("current_item", String.valueOf(viewPager.getCurrentItem()));
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        AddGroupDialog addGroupDialog = new AddGroupDialog();
                        addGroupDialog.show(getSupportFragmentManager(), "add_group_dialog");
                        break;
                    case 1:
                        AddGameSituationDialog addGameSituationDialog = new AddGameSituationDialog();
                        addGameSituationDialog.show(getSupportFragmentManager(), "add_game_situation_dialog");
                        break;
                }
            }
        });

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettings();
                break;
            case R.id.action_about:
                showAboutDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettings() {
        Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intentSettings);
    }

    private void showAboutDialog() {
        AboutDialogFragment aboutDialog = new AboutDialogFragment();
        aboutDialog.show(getSupportFragmentManager(), "dialog_fragment_about");
    }

    @Override
    public void addGroup(Group group) {
        GroupsFragment groupsFragment = (GroupsFragment) getCurrentFragment();
        groupsFragment.addGroup(group);
    }

    @Override
    public void addGameSituation(GameSituation gameSituation) {
        GameSituationsFragment gameSituationsFragment = (GameSituationsFragment) getCurrentFragment();
        gameSituationsFragment.addGameSituation(gameSituation);
    }

    private Fragment getCurrentFragment() {
        return (Fragment) pagerAdapter.instantiateItem(viewPager, viewPager.getCurrentItem());
    }

    private static class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new GroupsFragment();
            } else {
                return new GameSituationsFragment();
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Tréninky";
            } else {
                return "Cvičení";
            }
        }
    }
}
