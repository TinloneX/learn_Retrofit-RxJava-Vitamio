package org.tinlone.demo.rxjavasample.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.tinlone.demo.rxjavasample.R;

public class ViewCTestActivity extends AppCompatActivity {

    private static final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ctest);
    }
}
