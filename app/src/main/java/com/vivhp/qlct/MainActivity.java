package com.vivhp.qlct;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.vivhp.qlct.DBHelper.DataBaseHelper;
import com.vivhp.qlct.broadcast.broadcastAlarmKeyNote;
import com.vivhp.qlct.Model.Model_Phannhom;
import com.vivhp.qlct.Model.Model_Taikhoan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //This is our tab layout
    TabLayout tabLayout;

    //This is our viewPage
    ViewPager viewPager;

    //This is Toolbar
    Toolbar toolbar;

    //Notification Manager
    PendingIntent pendingIntent;

    DataBaseHelper dataBaseHelper;

    int currentPage;

    private int[] tabIcons = {
            R.mipmap.ic_mode_edit_white_24dp,
            R.mipmap.ic_content_paste_white_24dp,
            R.mipmap.ic_supervisor_account_white_24dp,
            R.mipmap.ic_more_white_24dp
    };
    private int[] tabIconsSelected = {
            R.mipmap.ic_mode_edit_white_24dp,
            R.mipmap.ic_content_paste_white_24dp,
            R.mipmap.ic_supervisor_account_white_24dp,
            R.mipmap.ic_more_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.Tab1);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());
//        addToTaikhoan();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        initNotification();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        setUpViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);

        //Initializing the tabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupTabIcons();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPage = tab.getPosition();

                if (currentPage == 0) {
//                    tabLayout.getTabAt(0).setIcon(tabIconsSelected[0]);
                    toolbar.setTitle(R.string.Tab1);
                }
                if (currentPage == 1) {
//                    tabLayout.getTabAt(1).setIcon(tabIconsSelected[1]);
                    toolbar.setTitle(R.string.Tab2);
                }
                if (currentPage == 2) {
//                    tabLayout.getTabAt(2).setIcon(tabIconsSelected[2]);
                    toolbar.setTitle(R.string.Tab3);
                }
                if (currentPage == 3) {
                    tabLayout.getTabAt(3).setIcon(tabIconsSelected[3]);
                    toolbar.setTitle(R.string.Tab4);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                currentPage = tab.getPosition();
//
//                if (currentPage == 0) {
//                    tabLayout.getTabAt(0).setIcon(tabIconsSelected[0]);
//                }
//                if (currentPage == 1) {
//                    tabLayout.getTabAt(1).setIcon(tabIconsSelected[1]);
//                }
//                if (currentPage == 2) {
//                    tabLayout.getTabAt(2).setIcon(tabIconsSelected[2]);
//                }
//                if (currentPage == 3) {
//                    tabLayout.getTabAt(3).setIcon(tabIconsSelected[3]);
//                }
            }
        });
    }


    void setUpViewPager(ViewPager viewPager) {
        //Creating our pager Adapter
        pageAdapter adapter = new pageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1(), "");
        adapter.addFragment(new Tab2(), "");
        adapter.addFragment(new Tab3(), "");
        adapter.addFragment(new Tab4(), "");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIconsSelected[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

//    private void addToTaikhoan(){
//        //insert taikhoan
//        Log.d("Inserting: ", "......");
//        dataBaseHelper.addTaikhoan(new Model_Taikhoan("Tien mat", 120000));
//        dataBaseHelper.addTaikhoan(new Model_Taikhoan("The tin dung", 220000));
//        dataBaseHelper.addTaikhoan(new Model_Taikhoan("Tiet Kiem", 320000));
//
//        Log.d("Reading: ", ".....");
//        List<Model_Taikhoan> taikhoanList = dataBaseHelper.getAllTaiKhoan();
//
//        for (Model_Taikhoan taikhoan : taikhoanList){
//            String log = "Loai Tai Khoan: "
//                    + taikhoan.getId()
//                    + ", Ten Loai TK: "
//                    + taikhoan.getTenloaitk()
//                    + ", So Tien: "
//                    + taikhoan.getSotien();
//            Log.d("Tai Khoan: ", log);
//        }
//
//        String tentk = dataBaseHelper.getTaikhoan(3).getTenloaitk().toString();
//
//        Toast.makeText(getApplicationContext(), tentk, Toast.LENGTH_LONG).show();
//
//    }

    class pageAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        //Constructor

        public pageAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    //initNotification
    public void initNotification(){
        Intent intent = new Intent(this, broadcastAlarmKeyNote.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long time = 60001;
        Toast.makeText(this, "Scheduled", Toast.LENGTH_SHORT).show();
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + time, time, pendingIntent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_add_acc) {
            // Intent -> Activity History
            startActivity(new Intent(this, AddAccActivity.class));
        } else if (id == R.id.nav_history) {
            // Intent -> Activity History
            startActivity(new Intent(this, ActivityHistory.class));

        } else if (id == R.id.nav_setting) {
            // Intent -> Activity Setting
            startActivity(new Intent(this, SettingActivity.class));
        } else if (id == R.id.nav_exit) {
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
