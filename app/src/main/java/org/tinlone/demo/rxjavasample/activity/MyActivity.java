package org.tinlone.demo.rxjavasample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.activity.view.ViewATestActivity;
import org.tinlone.demo.rxjavasample.activity.view.ViewBTestActivity;
import org.tinlone.demo.rxjavasample.activity.view.ViewCTestActivity;
import org.tinlone.demo.rxjavasample.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {

    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        List<String> data = new ArrayList<>();
        data.add("自定义控件学习1");
        data.add("自定义控件学习2");
        data.add("自定义控件学习3");

        List<Class> activities = new ArrayList<>();
        activities.add(ViewATestActivity.class);
        activities.add(ViewBTestActivity.class);
        activities.add(ViewCTestActivity.class);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new MyAdapter(this,data,activities));
    }

    private void initView() {
        rvList = (RecyclerView) findViewById(R.id.rv_list);
    }
}
