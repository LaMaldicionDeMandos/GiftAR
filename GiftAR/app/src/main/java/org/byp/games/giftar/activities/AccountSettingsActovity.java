package org.byp.games.giftar.activities;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.byp.games.giftar.R;
import org.byp.games.giftar.fragments.AccountSettingsFragment;

/**
 * Created by boot on 9/28/15.
 */
public class AccountSettingsActovity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AccountSettingsFragment()).commit();
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
    }
}
