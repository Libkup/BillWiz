package com.billwiz.admin.billwiz.Activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.billwiz.admin.billwiz.adapter.DrawerMonthViewRecyclerViewAdapter;
import com.billwiz.admin.billwiz.adapter.DrawerMonthViewRecyclerViewAdapter.OnItemClickListener;
import com.billwiz.admin.billwiz.adapter.MonthViewFragmentAdapter;
import com.billwiz.admin.billwiz.model.RecordManager;
import com.billwiz.admin.billwiz.util.BillWizUtil;
import com.billwiz.admin.billwiz.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.HeaderDesign;


/**
 * Created by 胡钰玺 on 2018/9/9
 */

public class AccountBookMonthViewActivity extends AppCompatActivity {

    private MaterialViewPager mViewPager;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;

    private MonthViewFragmentAdapter monthModeAdapter = null;

    private Context mContext;

    private RecyclerView recyclerView;
    private DrawerMonthViewRecyclerViewAdapter drawerMonthViewRecyclerViewAdapter;

    private TextView userName;
    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        setContentView(R.layout.activity_account_book_month_view);

        userName = (TextView)findViewById(R.id.user_name);
        userEmail = (TextView)findViewById(R.id.user_email);
        userName.setTypeface(BillWizUtil.typefaceLatoRegular);
        userEmail.setTypeface(BillWizUtil.typefaceLatoLight);

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        View view = mViewPager.getRootView();
        TextView title = (TextView)view.findViewById(R.id.logo_white);
        title.setTypeface(BillWizUtil.typefaceLatoLight);
        title.setText("BillWiz");

        mViewPager.getPagerTitleStrip().setTypeface(BillWizUtil.GetTypeface(), Typeface.NORMAL);

        setTitle("");

        toolbar = mViewPager.getToolbar();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);

        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                }
            });
        }

        monthModeAdapter = new MonthViewFragmentAdapter(getSupportFragmentManager());
        mViewPager.getViewPager().setOffscreenPageLimit(monthModeAdapter.getCount());
        mViewPager.getViewPager().setAdapter(monthModeAdapter);
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        if (monthModeAdapter.getCount() == 1) {
            mViewPager.getPagerTitleStrip().setVisibility(View.INVISIBLE);
        }

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                return HeaderDesign.fromColorAndDrawable(
                        BillWizUtil.GetTagColor(RecordManager.TAGS.get(page).getId()),
                        BillWizUtil.GetTagDrawable(-3)
                );
            }
        });

        recyclerView = (RecyclerView)mDrawer.findViewById(R.id.recycler_view);
        drawerMonthViewRecyclerViewAdapter = new DrawerMonthViewRecyclerViewAdapter(mContext);
        recyclerView.setAdapter(drawerMonthViewRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        drawerMonthViewRecyclerViewAdapter.SetOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mViewPager.getViewPager().setCurrentItem(position);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDrawer.closeDrawers();
                    }
                }, 700);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MaterialViewPagerHelper.unregister(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

}
