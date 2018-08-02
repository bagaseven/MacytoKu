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

    View.OnClickListener jadwal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            jadwal(v);
        }
    };

    public void jadwal(View v) {
        Intent intent = new Intent(MainActivity.this, Jadwal.class);
        startActivity(intent);
    }

    View.OnClickListener infor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            infor(v);
        }
    };

    public void infor(View v) {
        Intent intent = new Intent(MainActivity.this, Info.class);
        startActivity(intent);
    }

    View.OnClickListener rute = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            rute(v);
        }
    };

    public void rute(View v) {
        Intent intent = new Intent(MainActivity.this, Rute.class);
        startActivity(intent);
    }

    View.OnClickListener testi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            testi(v);
        }
    };

    public void testi(View v) {
        Intent intent = new Intent(MainActivity.this, Testi.class);
        startActivity(intent);
    }

    View.OnClickListener foto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            foto(v);
        }
    };

    public void foto(View v) {
        Intent intent = new Intent(MainActivity.this, Foto.class);
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


