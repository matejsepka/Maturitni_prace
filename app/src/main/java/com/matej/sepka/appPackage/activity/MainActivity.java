package com.matej.sepka.appPackage.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.matej.sepka.appPackage.dialog.AboutDialogMainActivity;
import com.matej.sepka.appPackage.dialog.AddDialogListener;
import com.matej.sepka.appPackage.dialog.AddGameSituationDialog;
import com.matej.sepka.appPackage.dialog.AddGroupDialog;
import com.matej.sepka.appPackage.database.GameSituation;
import com.matej.sepka.appPackage.fragment.GameSituationsFragment;
import com.matej.sepka.appPackage.database.Group;
import com.matej.sepka.appPackage.fragment.GroupsFragment;
import com.matej.sepka.appPackage.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements AddDialogListener {
    //implementace proměnných
    private static final int PAGE_COUNT = 2;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    //onCreate metoda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //další odkazy
        FloatingActionButton fab = findViewById(R.id.fab);
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        //přidávání skupin a cvičení
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (viewPager.getCurrentItem()) {
                    //pokud viewpager je na první stránce pak přidáme skupinu
                    case 0:
                        AddGroupDialog addGroupDialog = new AddGroupDialog();
                        addGroupDialog.show(getSupportFragmentManager(), "add_group_dialog");
                        break;
                    //pokud je viewpager na druhé stránce pak přidáme cvičení
                    case 1:
                        AddGameSituationDialog addGameSituationDialog = new AddGameSituationDialog();
                        addGameSituationDialog.show(getSupportFragmentManager(), "add_game_situation_dialog");
                        break;
                }
            }
        });

        //zavolání adaptéru
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    //přiřazení menu ke stránce
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //při kliknutí na jednotku v menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                showAboutDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //metoda pro načtení dialogu s nápovědou
    private void showAboutDialog() {
        AboutDialogMainActivity aboutDialog = new AboutDialogMainActivity();
        aboutDialog.show(getSupportFragmentManager(), "dialog_fragment_about");
    }

    //přenášení nové skupiny z dialogu přes mainActivity do Fragmentu
    @Override
    public void addGroup(Group group) {
        GroupsFragment groupsFragment = (GroupsFragment) getCurrentFragment();
        groupsFragment.addGroup(group);
    }

    //přenášení nového cvičení z dialogu přes mainActivity do Fragmentu
    @Override
    public void addGameSituation(GameSituation gameSituation) {
        GameSituationsFragment gameSituationsFragment = (GameSituationsFragment) getCurrentFragment();
        gameSituationsFragment.addGameSituation(gameSituation);
    }

    //motoda vratí požadovaný fragment
    private Fragment getCurrentFragment() {
        return (Fragment) pagerAdapter.instantiateItem(viewPager, viewPager.getCurrentItem());
    }

    //třída pager adaptéru
    private static class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        //načítá fragment podle pozice
        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new GroupsFragment();
            } else {
                return new GameSituationsFragment();
            }
        }

        //určí na kolik fragmentu má adaptér stránku rozdělit
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        //určí názvy jednotlivých fragmentu
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Skupiny";
            } else {
                return "Cvičení";
            }
        }
    }
}
