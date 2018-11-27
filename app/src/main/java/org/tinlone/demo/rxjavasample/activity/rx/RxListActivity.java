package org.tinlone.demo.rxjavasample.activity.rx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.adapter.TextListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RxListActivity extends AppCompatActivity {

    @BindView(R.id.rv_view)
    RecyclerView mRvView;
    @BindView(R.id.srl_fresh)
    SwipeRefreshLayout srlFresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_list);
        ButterKnife.bind(this);
        mRvView.setLayoutManager(new LinearLayoutManager(this));
        TextListAdapter adapter = new TextListAdapter(ObservableList.RX_TITLE);
        mRvView.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(RxListActivity.this, RxLogActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
        srlFresh.setOnRefreshListener(() -> {
            adapter.notifyDataSetChanged();
            srlFresh.setRefreshing(false);
        });
    }
}
