package org.byp.games.giftar.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.byp.games.giftar.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
