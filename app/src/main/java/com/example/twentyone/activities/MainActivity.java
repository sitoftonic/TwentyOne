package com.example.twentyone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.twentyone.R;
import com.example.twentyone.dialogs.AboutDialog;
import com.example.twentyone.fragments.AccountFragment;
import com.example.twentyone.fragments.EntitiesFragment;
import com.example.twentyone.fragments.HistoryFragment;
import com.example.twentyone.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    static String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fm = getSupportFragmentManager();

    private Toolbar toolbar;

    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return processNav(item);
        }
    };

    private int BACK_MODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            HomeFragment homeFragment = HomeFragment.getInstance();
            Bundle bundle = new Bundle();
            bundle.putString("user", getIntent().getExtras().getString("user"));
            homeFragment.setArguments(bundle);
            fm.beginTransaction()
                    .add(R.id.container_main, homeFragment, HomeFragment.TAG)
                    .commit();
        }

        String user = getIntent().getExtras().getString("user");

        Log.i("MAINNN", user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_about:
                new AboutDialog().show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("demo",1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        String tag = HomeFragment.TAG;

        if (fm.getBackStackEntryCount() > 0) {
            tag = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName();
        }

        if (BACK_MODE == 0) {
            fm.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.beginTransaction().replace(R.id.container_main, HomeFragment.getInstance(), HomeFragment.TAG).commit();
            navigation.getMenu().getItem(0).setChecked(true);
        } else {

            assert tag != null;

            if (tag.equals(HomeFragment.TAG)) {
                navigation.getMenu().getItem(0).setChecked(true);
            } else if (tag.equals(HistoryFragment.TAG)) {
                navigation.getMenu().getItem(1).setChecked(true);
            } else if (tag.equals(EntitiesFragment.TAG)) {
                navigation.getMenu().getItem(2).setChecked(true);
            } else if (tag.equals(AccountFragment.TAG)) {
                navigation.getMenu().getItem(3).setChecked(true);
            }
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        fm.beginTransaction()
                .replace(R.id.container_main, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    private void switchFragment(Fragment fragment, String tag) {

        final int size = fm.getBackStackEntryCount();

        if (size > 0) {
            if (!fm.getBackStackEntryAt(size - 1).getName().equals(tag)) {
                replaceFragment(fragment, tag);
            }
        } else {
            replaceFragment(fragment, tag);
        }
    }

    public void switchToHome() {
        if (fm.getBackStackEntryCount() > 0) {
            switchFragment(HomeFragment.getInstance(), HomeFragment.TAG);
        }
    }

    public void switchToHistory() {
        switchFragment(HistoryFragment.getInstance(), HistoryFragment.TAG);
    }

    public void switchToEntities() {
        switchFragment(EntitiesFragment.getInstance(), EntitiesFragment.TAG);
    }

    public void switchToAccount() {
        switchFragment(AccountFragment.getInstance(), AccountFragment.TAG);
    }

    private boolean processNav(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                switchToHome();
                return true;
            case R.id.navigation_history:
                switchToHistory();
                return true;
            case R.id.navigation_entities:
                switchToEntities();
                return true;
            case R.id.navigation_account:
                switchToAccount();
                return true;
            default:
                return false;
        }
    }
}
