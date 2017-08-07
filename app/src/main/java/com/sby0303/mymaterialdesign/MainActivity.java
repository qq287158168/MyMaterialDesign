package com.sby0303.mymaterialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sby0303.mymaterialdesign.myutils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private FruitAdapter adapter;

    private List<Fruit> fruitList = new ArrayList<>();

    private Fruit[] fruits = {
            new Fruit("苹果", R.mipmap.apple),
            new Fruit("香蕉", R.mipmap.banana),
            new Fruit("菠萝", R.mipmap.pineapple),
            new Fruit("葡萄", R.mipmap.grape),
            new Fruit("桔子", R.mipmap.orange),
            new Fruit("芒果", R.mipmap.mango),
            new Fruit("樱桃", R.mipmap.cherry),
            new Fruit("梨", R.mipmap.pear),
            new Fruit("西瓜", R.mipmap.watermelon),
            new Fruit("草莓", R.mipmap.strawberry),
    };


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);


        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                drawerLayout.closeDrawers();
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "要删除数据？", Snackbar.LENGTH_SHORT)
                        .setAction("返回", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.show(MainActivity.this, "数据未删除");
                            }
                        }).show();

            }
        });

        initFruits();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });

            }
        }).start();

    }

    private void initFruits() {

        fruitList.clear();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.backup:

                ToastUtil.show(this, "点击了backup");
                break;
            case R.id.delete:
                ToastUtil.show(this, "点击了delete");
                break;
            case R.id.settings:
                ToastUtil.show(this, "点击了setting");
                break;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }


        return true;
    }


}































