package org.byp.games.giftar.providers;

import com.firebase.client.Firebase;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import org.byp.games.giftar.R;

import roboguice.inject.InjectResource;

/**
 * Created by boot on 10/2/15.
 */
@Singleton
public class FirebaseProvider implements Provider<Firebase> {
    private final static String TAG = FirebaseProvider.class.getSimpleName();
    @InjectResource(R.string.firebase_url)
    private String url;

    private Firebase firebase;

    @Override
    public Firebase get() {
        if (firebase == null) {
            firebase = new Firebase(url);
        }
        return firebase;
    }
}
