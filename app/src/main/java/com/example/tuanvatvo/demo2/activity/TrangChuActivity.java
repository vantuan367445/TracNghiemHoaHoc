package com.example.tuanvatvo.demo2.activity;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tuanvatvo.demo2.R;
import com.example.tuanvatvo.demo2.common.CheckConnectiom;

public class TrangChuActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        myHandler.postDelayed(checkConnection,5000);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentChemistry_Home()).commit();
        }

        addControls();
        addEvents();




    }
    private Runnable checkConnection = new Runnable() {
        @Override
        public void run() {
            if(CheckConnectiom.checkCon(getApplicationContext()) == false){
                AlertDialog.Builder builder = new AlertDialog.Builder(TrangChuActivity.this);
                builder.setTitle("Không có kết nối internet !");
                builder.setCancelable(false);
                builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TrangChuActivity.this.finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else
            myHandler.postDelayed(checkConnection,5000);

        }
    };

    private void addControls() {
        bottomNav = findViewById(R.id.bottom_navigation);

    }

    private void addEvents() {
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new FragmentChemistry_Home();
                        break;
                    case R.id.nav_account:
                        selectedFragment = new FragmentHistory_Home();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                return true;
            }
        });
    }
}
