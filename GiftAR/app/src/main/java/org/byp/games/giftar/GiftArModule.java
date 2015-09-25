package org.byp.games.giftar;

import com.google.inject.Binder;
import com.google.inject.Module;

import org.byp.games.giftar.services.DefaultPreferencesService;
import org.byp.games.giftar.services.PreferencesService;

/**
 * Created by boot on 9/24/15.
 */
public class GiftArModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(PreferencesService.class).to(DefaultPreferencesService.class);
    }
}
