package com.example.kideng.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.databinding.ActivityMainBinding;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.UserDao;
import com.example.kideng.db.entities.User;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    final static String showWelcomeScreenString = "showWelcome";

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        boolean mWelcomeScreenShown = mPrefs.getBoolean(showWelcomeScreenString, true);
        if (mWelcomeScreenShown) {
            AppDatabase db = App.getInstance().getDatabase();
            UserDao userDao = db.userDao();
            userDao.insert(new User(0, 0, 0, 0));

            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putBoolean(showWelcomeScreenString, false);
            editor.apply();
        } else {
            DrawerLayout drawer = binding.drawerLayout;
            NavigationView navigationView = binding.navView;

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_main, R.id.nav_statistics, R.id.nav_settings, R.id.nav_support, R.id.nav_about_us)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void gameClick (View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void themeClick (View view){
        Intent intent = new Intent(this, ChooseThemeActivity.class);
        startActivity(intent);
    }

}