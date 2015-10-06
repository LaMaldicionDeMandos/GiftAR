package org.byp.games.giftar.services;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.plus.model.people.Person;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.byp.games.giftar.model.User;
import org.byp.games.giftar.model.UserProfile;
import org.byp.games.giftar.model.UserState;

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

    public void signUp(final String accountId, final Person profile) {
        final String userId = accountId.replaceAll("\\.","");
        firebase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Data changed " + dataSnapshot);
                if (!dataSnapshot.exists()) {
                    User user = new User(userId, profile.getDisplayName(), profile.getImage(),
                            new UserProfile(UserState.ACTIVE));
                    firebase.child(userId).setValue(user.getProfile());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "Data canceled " + firebaseError);
            }
        });
        //firebase.child(user).setValue("USER");
    }
}
