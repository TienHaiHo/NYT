package com.example.dkkbg_000.nytretrofit.view_news;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.dkkbg_000.nytretrofit.R;
import com.example.dkkbg_000.nytretrofit.adapter.Adapter_MainActivity;
import com.example.dkkbg_000.nytretrofit.fragment.FragmentDrawer;
import com.example.dkkbg_000.nytretrofit.model.Result;
import com.example.dkkbg_000.nytretrofit.presenter.CallbackLoadNews;
import com.example.dkkbg_000.nytretrofit.presenter.LoadBussiness;
import com.example.dkkbg_000.nytretrofit.presenter.SolveTimerReload;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    final String key = "ffd170d7fe4246c69413fb97a1b0ce05";
    private FragmentDrawer drawerFragment;
    private Toolbar mToolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        final Context c = this;

        LoadBussiness newsBussiness = new LoadBussiness(key);
        newsBussiness.getListNews(new CallbackLoadNews() {
            @Override
            public void onSuccess(List<Result> results) {
                recyclerView.setAdapter(new Adapter_MainActivity(results, R.layout.item_news, c));
            }

            @Override
            public void onFaill(List<Result> error) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });


        //From Here Starts All The Swipe To
        // Refresh Initialisation And Setter Methods.
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);//Initialising
        //Setting Up OnRefresh Listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                //onRefresh method is used to perform all the action
                // when the swipe gesture is performed.
                try {
//                    addTime();//addTime() Method is called
                    SolveTimerReload reload = new SolveTimerReload(key);
                    reload.addTime(new CallbackLoadNews() {
                        @Override
                        public void onSuccess(List<Result> results) {
                            recyclerView.setAdapter(new Adapter_MainActivity(results, R.layout.item_news, c));
                            if (swipeRefreshLayout.isRefreshing())
                                swipeRefreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onFaill(List<Result> error) {
                            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        swipeRefreshLayout.setColorSchemeResources(
                R.color.blue,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);     //refresh.

        //setSize() Method Sets The Size Of Loader
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        //Below Method Will set background color of Loader
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onDrawerItemSelected(View view, int position)  {
        //displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new BussinessFragment();
//                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new ArtFragment();
//                title = getString(R.string.title_friends);
                break;
            case 2:
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}
