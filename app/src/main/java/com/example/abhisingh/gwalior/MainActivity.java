package com.example.abhisingh.gwalior;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Nullable ListView all_list;
    ImageSwitcher is;
    int[] gal = {R.drawable.is1 , R.drawable.is2 , R.drawable.is3};
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        is = (ImageSwitcher)findViewById(R.id.iSwitcher);
        is.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView()
            {
                return new ImageView(MainActivity.this);
            }

        });
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        is.setInAnimation(fadeIn);
        is.setOutAnimation(fadeOut);
        start();

    }

    public void start()
    {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {

                        is.setImageResource(gal[pos]);
                        pos++;
                        if(pos==2)
                            pos=0;


                    }
                });

            }
        },0,3000);
    }



    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        all_list = (ListView)findViewById(R.id.m_list);

        if (id == R.id.home)
        {

            all_list.setVisibility(View.INVISIBLE);
        }

        else if(id==R.id.monuments || id==R.id.museums || id==R.id.hotels || id==R.id.eating_joints || id==R.id.pubs)
        {

            all_list.setVisibility(View.VISIBLE);

            String[] str={};

           if (id == R.id.monuments)
                str = getResources().getStringArray(R.array.mons);

            else if (id == R.id.museums)
                str = getResources().getStringArray(R.array.mus);

            else if (id == R.id.hotels)
                str = getResources().getStringArray(R.array.hot);

            else if (id == R.id.eating_joints)
                str = getResources().getStringArray(R.array.eat);

            else if (id == R.id.pubs)
                str = getResources().getStringArray(R.array.pub);

            ListAdapter aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str);


            all_list.setAdapter(aAdapter);
            all_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent i =new Intent(getApplication(),Particulars.class);
                    String selected =(String) (all_list.getItemAtPosition(position));
                    i.putExtra("name",selected);
                    startActivity(i);
                }
            });

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
