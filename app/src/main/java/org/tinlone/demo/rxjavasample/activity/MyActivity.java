package org.tinlone.demo.rxjavasample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.adapter.MyAdapter;
import org.tinlone.demo.rxjavasample.config.Configs;

/**
 * @author Administrator
 */
public class MyActivity extends AppCompatActivity {

    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new MyAdapter());
    }

    private void initView() {
        rvList = findViewById(R.id.rv_list);
    }
}
