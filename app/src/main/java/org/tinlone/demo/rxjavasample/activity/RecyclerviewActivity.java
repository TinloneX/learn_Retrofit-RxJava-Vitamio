package org.tinlone.demo.rxjavasample.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.adapter.RecyclerAdapter;
import org.tinlone.demo.rxjavasample.bean.CallLogBean;

import java.util.ArrayList;

public class RecyclerviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<CallLogBean> mCallLogBeen;
    private RecyclerAdapter mRecyclerAdapter;
    private ImageButton bottom;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHeight = this.getWindowManager().getDefaultDisplay().getHeight();

        setRecyclerView();
    }

    private void initData() {
        mCallLogBeen = new ArrayList<>();
        CallLogBean bean = new CallLogBean();
        bean.setToName("张三");
        bean.setToPhone("1008611");
        bean.setCallTime("1分26秒");
        bean.setCallType("去电");
        bean.setDateTime("2017-08-15 23:59:59");
        for (int i = 0; i < 100; i++) {
            mCallLogBeen.add(bean);
        }
        mRecyclerAdapter = new RecyclerAdapter(this, mCallLogBeen);
        recyclerView.setAdapter(mRecyclerAdapter);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        bottom = ((ImageButton) findViewById(R.id.imageButton));

    }

    private ObjectAnimator footerAnim;

    private void setRecyclerView() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            int countY = 0;
            boolean down = false;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                float bottomY = RecyclerviewActivity.this.bottom.getY();
                int bottomHeight = bottom.getHeight();
                if (footerAnim == null) {
                    footerAnim = ObjectAnimator.ofFloat(bottom, "translationY", 0, mHeight);
                }
                countY += dy;
                if (dy > 0) {
                    countY += dy;
                    Log.i("zjl", "(RecyclerviewActivity.java:89)--onScrolled: countY = " + countY);
                    if (!down && countY > 100) {
                        footerAnim.start();
                        countY = 0;
                        down = true;
                        Log.i("zjl", "(RecyclerviewActivity.java:95)--onScrolled: 下去");
                    }

                } else {
                    if (down) {
                        footerAnim.reverse();
                        down = false;
                        Log.i("zjl", "(RecyclerviewActivity.java:103)--onScrolled: 上来");
                    }else {
                        Log.i("zjl", "(RecyclerviewActivity.java:105)--onScrolled: 没下去，不用上来");
                    }
                    countY = 0;
                }
            }
        });
    }
}
