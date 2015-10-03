package org.byp.games.giftar;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import roboguice.RoboGuice;

import static org.byp.games.giftar.R.string.analytics_id;

/**
 * Created by boot on 9/24/15.
 */
public class GiftARApplication extends Application {
    public final static String MASTER_USER_KEY = "master_user";
    protected static GoogleAnalytics analytics;
    protected static Tracker tracker;
    @Override
    public void onCreate() {
        super.onCreate();
        analytics = GoogleAnalytics.getInstance(this);
        tracker = analytics.newTracker(analytics_id);
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);
        Firebase.setAndroidContext(this);
        RoboGuice.setUseAnnotationDatabases(false);
        RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), new GiftArModule());
    }
}
