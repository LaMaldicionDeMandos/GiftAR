package org.byp.games.giftar;

import com.firebase.client.Firebase;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.inject.Binder;
import com.google.inject.Module;

import org.byp.games.giftar.providers.FirebaseProvider;
import org.byp.games.giftar.services.DefaultPreferencesService;
import org.byp.games.giftar.services.PreferencesService;

import static org.byp.games.giftar.GiftARApplication.analytics;
import static org.byp.games.giftar.GiftARApplication.tracker;

/**
 * Created by boot on 9/24/15.
 */
public class GiftArModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(PreferencesService.class).to(DefaultPreferencesService.class);
        binder.bind(GoogleAnalytics.class).toInstance(analytics);
        binder.bind(Tracker.class).toInstance(tracker);
        binder.bind(Firebase.class).toProvider(FirebaseProvider.class);
    }
}
