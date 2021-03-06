package com.unlogicon.typegram.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.unlogicon.typegram.R;
import com.unlogicon.typegram.adapters.MainRecyclerViewAdapter;
import com.unlogicon.typegram.interfaces.activities.MainActivityView;
import com.unlogicon.typegram.presenters.activities.MainActivityPresenter;
import com.unlogicon.typegram.views.EndlessRecyclerViewScrollListener;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    @InjectPresenter
    MainActivityPresenter presenter;

    private RecyclerView recyclerView;

    public static final int ACTIVITY_EDITOR = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onLoadMore(page, totalItemsCount, view);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        presenter.setMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAdapter(MainRecyclerViewAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyDataSetChanged(MainRecyclerViewAdapter adapter) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addOnScrollListener(EndlessRecyclerViewScrollListener listener) {
        recyclerView.addOnScrollListener(listener);
    }

    @Override
    public void startActivityLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void startActivityArticleEditor() {
        Intent intent = new Intent(this, ArticleEditorActivity.class);
        startActivityForResult(intent, ACTIVITY_EDITOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
