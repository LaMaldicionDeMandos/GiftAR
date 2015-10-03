package org.byp.games.giftar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.inject.Inject;

import org.byp.games.giftar.R;
import org.byp.games.giftar.services.AnalitycsService;
import org.byp.games.giftar.services.PreferencesService;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

import static org.byp.games.giftar.GiftARApplication.MASTER_USER_KEY;
import static org.byp.games.giftar.activities.ActivityUtils.getGoogleClient;
import static org.byp.games.giftar.services.AnalitycsService.AnalitycsCategory.UX;

/**
 * Created by boot on 9/24/15.
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends RoboActivity implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener {
    private final static String TAG = SplashActivity.class.getSimpleName();
    public final static int RESPONSE = 1;
    public static final String LOGIN = "login";
    public static final String SIGN_UP = "sign up";

    private GoogleApiClient googleClient;

    @Inject
    private AnalitycsService analitycs;

    @Inject
    private Firebase firebase;

    @Inject
    private PreferencesService preferences;

    @Override
    protected void onCreate(Bundle savedInterfaceState) {
        super.onCreate(savedInterfaceState);
        googleClient = getGoogleClient(this, this, this);
        firebase.child("user").setValue("Conectado!!");
        if(!preferences.contain(MASTER_USER_KEY)) {
            Log.d(TAG, "Todavia no se registro ningun usuario");
            startActivityForResult(new Intent(this, LoginActivity.class), RESPONSE);
        } else {
            Log.d(TAG, "Ya existe un usuario registrado");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleClient.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Log.d(TAG, "Registro ok");
            googleClient.connect();
        } else {
            Log.d(TAG, "Registro fail");
            Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection Failed: " + connectionResult);
        startActivityForResult(new Intent(this, LoginActivity.class), RESPONSE);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "Connected");
        saveUserAccount();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Suspended?");
    }

    private void saveUserAccount() {
        String account = Plus.AccountApi.getAccountName(googleClient);
        if (account != null && !preferences.contain(MASTER_USER_KEY)) {
            Log.d(TAG, "Save google account: " + account);
            persistUserOnPreferences(account);
            analitycs.track(UX, LOGIN, SIGN_UP);

            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Nope", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void persistUserOnPreferences(final String user) {
        preferences.put(MASTER_USER_KEY, user);
    }
}
