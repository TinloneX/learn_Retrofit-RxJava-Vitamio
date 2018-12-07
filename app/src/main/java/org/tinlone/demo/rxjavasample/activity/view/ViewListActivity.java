package org.tinlone.demo.rxjavasample.activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.adapter.TextListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class ViewListActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        TextListAdapter adapter = new TextListAdapter();
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(ViewListActivity.this, ViewATestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        rvList.setAdapter(adapter);
    }

    @OnClick(R.id.iv_back)
    public void onBackClick(){
        finish();
    }
}
