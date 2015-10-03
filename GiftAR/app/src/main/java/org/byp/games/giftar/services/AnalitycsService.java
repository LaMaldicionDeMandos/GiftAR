package org.byp.games.giftar.services;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by boot on 10/2/15.
 */
@Singleton
public class AnalitycsService {
    public static enum AnalitycsCategory {
        UX
    }

    private final Tracker tracker;

    @Inject
    public AnalitycsService(final Tracker tracker) {
        this.tracker = tracker;
    }

    public void track(final AnalitycsCategory category, final String action, final String label) {
        tracker.send(new HitBuilders.EventBuilder()
        .setCategory(category.toString())
        .setAction(action)
        .setLabel(label).build());
    }
}
