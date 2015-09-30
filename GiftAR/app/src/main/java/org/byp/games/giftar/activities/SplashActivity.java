package org.byp.games.giftar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.inject.Inject;

import org.byp.games.giftar.R;
import org.byp.games.giftar.services.PreferencesService;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

import static org.byp.games.giftar.GiftARApplication.MASTER_USER_KEY;
import static org.byp.games.giftar.activities.ActivityUtils.getGoogleClient;

/**
 * Created by boot on 9/24/15.
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends RoboActivity implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener {
    private final static String TAG = SplashActivity.class.getSimpleName();
    public final static int RESPONSE = 1;

    private GoogleApiClient googleClient;

    @Inject
    private PreferencesService preferences;

    @Override
    protected void onCreate(Bundle savedInterfaceState) {
        super.onCreate(savedInterfaceState);
        googleClient = getGoogleClient(this, this, this);
        if(!preferences.contain(MASTER_USER_KEY)) {
            Log.d(TAG, "Todavia no se registro ningun usuario");
            startActivityForResult(new Intent(this, LoginActivity.class), RESPONSE);
        } else {
            Log.d(TAG, "Ya existe un usuario registrado");
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
        Log.d(TAG, "Connection Failed");
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
        Person currentPerson = Plus.PeopleApi.getCurrentPerson(googleClient);
        if (currentPerson != null) {
            String personName = currentPerson.getDisplayName();
            String personPhoto = currentPerson.getImage().getUrl();
            String personGooglePlusProfile = currentPerson.getUrl();
            Toast.makeText(this, personName, Toast.LENGTH_LONG).show();
            Toast.makeText(this, personPhoto, Toast.LENGTH_LONG).show();
            Toast.makeText(this, personGooglePlusProfile, Toast.LENGTH_LONG).show();
            Log.d(TAG, "User name: " + personName);
            Log.d(TAG, "User photo: " + personPhoto);
            Log.d(TAG, "User url: " + personGooglePlusProfile);
        } else {
            Toast.makeText(this, "Nope", Toast.LENGTH_LONG).show();
        }
    }
}
