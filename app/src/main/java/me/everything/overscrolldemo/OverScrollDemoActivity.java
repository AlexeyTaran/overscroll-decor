package me.everything.overscrolldemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.everything.overscrolldemo.view.GridViewDemoFragment;
import me.everything.overscrolldemo.view.ListViewDemoFragment;
import me.everything.overscrolldemo.view.MiscViewsDemoFragment;
import me.everything.overscrolldemo.view.RecyclerViewDemoFragment;
import me.everything.overscrolldemo.view.ScrollViewDemoFragment;

public class OverScrollDemoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overscroll_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.grid_view_demo_title);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer_nav);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_placeholder, new GridViewDemoFragment())
                .commit();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final int id = item.getItemId();
        item.setChecked(true);

        switch (id) {
            case R.id.drawer_item_gridview_demo:
                replaceFragment(new GridViewDemoFragment(), R.string.grid_view_demo_title);
                break;
            case R.id.drawer_item_listview_demo:
                replaceFragment(new ListViewDemoFragment(), R.string.list_view_demo_title);
                break;
            case R.id.drawer_item_recyclerview_demo:
                replaceFragment(new RecyclerViewDemoFragment(), R.string.recycler_view_demo_title);
                break;
            case R.id.drawer_item_scrollview_demo:
                replaceFragment(new ScrollViewDemoFragment(), R.string.scroll_view_demo_title);
                break;
            case R.id.drawer_item_misc_demo:
                replaceFragment(new MiscViewsDemoFragment(), R.string.misc_views_demo_title);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment, int titleResId) {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.fade_in_slow, R.animator.fade_out_quick)
                .replace(R.id.fragment_placeholder, fragment)
                .commit();
        getSupportActionBar().setTitle(titleResId);
    }
}
