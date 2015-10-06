package org.byp.games.giftar.model;

import com.google.api.client.repackaged.com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by boot on 10/5/15.
 */
public class UserProfile {
    private UserState state;
    private List<Gift> gifts;

    public UserProfile(){
        this(UserState.PENDING);
    }

    public UserProfile(final UserState state) {
        this(state, Lists.<Gift>newArrayList());
    }

    public UserProfile(final UserState state, final List<Gift> gifts) {
        this.state = state;
        this.gifts = gifts;
    }

    public UserState getState() {
        return state;
    }

    public void activate() {
        this.state = UserState.ACTIVE;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(UserProfile.class)
                .add("state", state)
                .toString();
    }
}
