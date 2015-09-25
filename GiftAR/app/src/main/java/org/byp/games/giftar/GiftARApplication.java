package org.byp.games.giftar;

import android.app.Application;

import roboguice.RoboGuice;

/**
 * Created by boot on 9/24/15.
 */
public class GiftARApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RoboGuice.setUseAnnotationDatabases(false);
        RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), new GiftArModule());
    }
}
