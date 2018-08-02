package com.example.asus.macytoku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment homeFragment = new HomeFragment();
    Fragment notificationsFragment = new NotificationsFragment();
    Fragment settingsFragment = new SettingsFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();

    View.OnClickListener tes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            myFancyMethod(v);
        }
    };

    public void myFancyMethod(View v) {
        Intent intent = new Intent(MainActivity.this, JadwalPesan.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigasi_home:
                        setMenuFragment(homeFragment);
                        break;
                    case R.id.navigasi_notifications:
                        setMenuFragment(notificationsFragment);
                        break;
                    case R.id.navigasi_settings:
                        setMenuFragment(settingsFragment);
                        break;

                }
                return true;
            }
        });

    }

    void setMenuFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}


