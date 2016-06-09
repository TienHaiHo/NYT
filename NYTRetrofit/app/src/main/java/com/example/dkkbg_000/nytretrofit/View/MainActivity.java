package com.example.dkkbg_000.nytretrofit.View;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.dkkbg_000.nytretrofit.Pojo.NYT;
import com.example.dkkbg_000.nytretrofit.Pojo.Result;
import com.example.dkkbg_000.nytretrofit.R;
import com.example.dkkbg_000.nytretrofit.adapter;
import com.example.dkkbg_000.nytretrofit.fragment.FragmentDrawer;
import com.example.dkkbg_000.nytretrofit.presenter.LoadBussiness;
import com.example.dkkbg_000.nytretrofit.rest.apiInterfaceNYT;
import com.example.dkkbg_000.nytretrofit.rest.apiNYT;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


//        apiInterfaceNYT apiService =
//                apiNYT.apiClient().create(apiInterfaceNYT.class);
//
//        Call<NYT> call = apiService.topNews(key);
//        call.enqueue(new Callback<NYT>() {
//            @Override
//            public void onResponse(Call<NYT> call, Response<NYT> response) {
//                List<Result> news = response.body().getResults();
//                Log.d("checkxyz: ", String.valueOf(news.size()));
//                recyclerView.setAdapter(new adapter(news, R.layout.item_news, getApplicationContext()));
//            }
//
//            @Override
//            public void onFailure(Call<NYT> call, Throwable t) {
//                Log.e("fail", t.toString());
//            }
//        });

        LoadBussiness newsBussiness = new LoadBussiness(key);
        List<Result> listNewsBussiness = newsBussiness.getListNews();
        Log.d("list news", String.valueOf(listNewsBussiness));
        recyclerView.setAdapter(new adapter(newsBussiness.getListNews(), R.layout.item_news, getApplicationContext()));

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
                    addTime();//addTime() Method is called
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //This are some optional methods for customizing
        // the colors and size of the loader.
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

    public void addTime() throws InterruptedException {
        //Here I am using a Handler to perform the refresh
        // action after some time to show some fake time
        // consuming task is being performed.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //EveryTime when this method is called
                // time at that instant will be added to
                // the ArrayList. This is just to populate
                // the recycler view.
//                myAdapter.timings.add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//                myAdapter.notifyItemInserted(myAdapter.timings.size()-1);
                apiInterfaceNYT apiService =
                        apiNYT.apiClient().create(apiInterfaceNYT.class);

                Call<NYT> call = apiService.topNews(key);
                call.enqueue(new Callback<NYT>() {
                    @Override
                    public void onResponse(Call<NYT> call, Response<NYT> response) {
                        List<Result> news = response.body().getResults();
                        Log.d("checkxyz: ", String.valueOf(news.size()));
                        recyclerView.setAdapter(new adapter(news, R.layout.item_news, getApplicationContext()));
                    }

                    @Override
                    public void onFailure(Call<NYT> call, Throwable t) {
                        Log.e("fail", t.toString());
                    }
                });
                //setRefreshing(false) method is called to stop
                // the refreshing animation view.
                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
            }
        }, 4000);
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
//                fragment = new MessagesFragment();
//                title = getString(R.string.title_messages);
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
