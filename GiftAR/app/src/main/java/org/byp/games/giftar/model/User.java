package org.byp.games.giftar.model;

import com.google.android.gms.plus.model.people.Person;
import com.google.api.client.repackaged.com.google.common.base.Objects;

/**
 * Created by boot on 10/5/15.
 */
public class User {
    private final String id;
    private final String name;
    private final Person.Image avatar;
    private final UserProfile profile;

    public User(final String id, final String name, final Person.Image avatar, final UserProfile profile) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Person.Image getAvatar() {
        return avatar;
    }

    public UserProfile getProfile() {
        return profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User u = (User)o;
        return Objects.equal(id, u.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(User.class)
                .add("id", id)
                .add("name", name)
                .add("avatar", avatar)
                .add("profile", profile)
                .toString();
    }
}
