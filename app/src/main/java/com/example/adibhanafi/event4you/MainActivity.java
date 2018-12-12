package com.example.adibhanafi.event4you;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNev;
    private FrameLayout mMainFrame;

    private HomeFragment HomeFragment;
    private StateFragment StateFragment;
    private NearbyFragment NearbyFragment;
    private CalendarFragment CalendarFragment;


    @Override
    protected void onStart() {
        super.onStart();
//        setContentView(R.layout.activity_main);
//        HomeFragment = new HomeFragment();
//        mMainFrame =  findViewById(R.id.main_frame);
//        mMainNev.setItemBackgroundResource(R.color.colorPrimary);
//
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.main_frame, HomeFragment);
//        fragmentTransaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame =  findViewById(R.id.main_frame);
        mMainNev =  findViewById(R.id.main_nev);

        HomeFragment = new HomeFragment();
        StateFragment = new StateFragment();
        NearbyFragment = new NearbyFragment();
        CalendarFragment = new CalendarFragment();

        mMainNev.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nev_home :
                        mMainNev.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(HomeFragment);
                        return true;

                    case R.id.nev_state :
                        mMainNev.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(StateFragment);
                        return true;

                    case R.id.nev_nearby :
                        mMainNev.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(NearbyFragment);
                        return true;

                    case R.id.nev_calendar :
                        mMainNev.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(CalendarFragment);
                        return true;

                    default:
                        return false;
                }
            }

            private void setFragment(Fragment Fragment) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                FragmentTransaction replace = fragmentTransaction.replace(R.id.main_frame, Fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
