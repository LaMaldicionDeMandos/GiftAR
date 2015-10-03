package org.byp.games.giftar.services;

import com.firebase.client.Firebase;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.json.JSONObject;

/**
 * Created by boot on 10/2/15.
 */
@Singleton
public class Services {
    private final Firebase firebase;

    @Inject
    public Services(final Firebase firebase) {
        this.firebase = firebase;
    }

    public void signUp(final String account) {
        String user = account.replaceAll("\\.","");
        firebase.child(user).setValue("USER");
    }
}
