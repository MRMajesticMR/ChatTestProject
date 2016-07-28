package ru.majesticinc.cometchattestproject.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ru.majesticinc.cometchattestproject.R;
import ru.majesticinc.cometchattestproject.ui.fragments.PublicChatFragment;
import ru.majesticinc.cometchattestproject.ui.fragments.UsersFragment;

public class WorkingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public enum State {
        PUBLIC_CHAT,
        USERS
    }

    private State activityState;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeActivityState(State.PUBLIC_CHAT);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_public_chat:
                changeActivityState(State.PUBLIC_CHAT);
                break;

            case R.id.nav_users:
                changeActivityState(State.USERS);
                break;

            case R.id.nav_exit:
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //===== <PRIVATE_METHODS> =====
    private void changeActivityState(State newState) {
        if(activityState != newState) {
            this.activityState = newState;

            Fragment stateFragment = null;

            switch (activityState) {
                case PUBLIC_CHAT:
                    stateFragment = PublicChatFragment.newInstance();
                    break;

                case USERS:
                    stateFragment = UsersFragment.newInstance();
                    break;
            }

            changeFragment(stateFragment);

            if(fab.isShown())
                fab.hide();

            if (stateFragment instanceof View.OnClickListener) {
                fab.setOnClickListener((View.OnClickListener) stateFragment);
                fab.show();
            } else {
                fab.setOnClickListener(null);
            }
        }
    }

    private void changeFragment(Fragment newFragment) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.working_activity_fragments_container, newFragment);
        tr.commit();
    }
    //===== </PRIVATE_METHODS> =====
}
