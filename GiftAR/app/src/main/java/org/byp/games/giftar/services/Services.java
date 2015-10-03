package org.byp.games.giftar.services;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by boot on 10/2/15.
 */
@Singleton
public class Services {
    private final static String TAG = Services.class.getSimpleName();
    private final Firebase firebase;

    @Inject
    public Services(final Firebase firebase) {
        this.firebase = firebase;
    }

    public void signUp(final String account) {
        String user = account.replaceAll("\\.","");
        firebase.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Data changed " + dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "Data canceled " + firebaseError);
            }
        });
        //firebase.child(user).setValue("USER");
    }
}
