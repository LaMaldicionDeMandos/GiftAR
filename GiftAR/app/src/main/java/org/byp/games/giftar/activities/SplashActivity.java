package org.byp.games.giftar.activities;

import android.content.Intent;
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
    public final static int RESPONSE = 1;
    public final static int FAILURE_RESPONSE = 2;

    @Inject
    PreferencesService preferences;
    @Override
    protected void onCreate(Bundle savedInterfaceState) {
        super.onCreate(savedInterfaceState);
        if(!preferences.contain(MASTER_USER_KEY)) {
            Log.d(TAG, "Todavia no se registro ningun usuario");
            startActivityForResult(new Intent(this, LoginActivity.class), RESPONSE);
        } else {
            Log.d(TAG, "Ya existe un usuario registrado");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Log.d(TAG, "Registro ok");
        } else {
            Log.d(TAG, "Registro fail");
            finish();
        }
    }
}
