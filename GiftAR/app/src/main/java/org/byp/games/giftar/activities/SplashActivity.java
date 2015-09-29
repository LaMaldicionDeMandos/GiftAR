package org.byp.games.giftar.activities;

import android.os.Bundle;
import android.util.Log;

import com.google.inject.Inject;

import org.byp.games.giftar.R;
import org.byp.games.giftar.services.PreferencesService;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

import static org.byp.games.giftar.GiftARApplication.MASTER_USER_KEY;

/**
 * Created by boot on 9/24/15.
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends RoboActivity {
    private final static String TAG = SplashActivity.class.getSimpleName();

    @Inject
    PreferencesService preferences;
    @Override
    protected void onCreate(Bundle savedInterfaceState) {
        super.onCreate(savedInterfaceState);
        if(!preferences.contain(MASTER_USER_KEY)) {
            Log.d(TAG, "Todavia no se registro ningun usuario");
        } else {
            Log.d(TAG, "Ya existe un usuario registrado");
        }
    }
}
