package org.byp.games.giftar.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import static org.byp.games.giftar.R.xml.pref_general;

/**
 * Created by boot on 9/28/15.
 */
public class AccountSettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(pref_general);
    }
}
