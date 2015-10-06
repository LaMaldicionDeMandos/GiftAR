package org.byp.games.giftar.model;

import com.google.api.client.repackaged.com.google.common.base.Objects;

import java.util.UUID;

/**
 * Created by boot on 10/5/15.
 */
public class Gift {
    private final String id;
    private final String url;
    private final double lat;
    private final double lon;
    private final double alt;

    public Gift(final String url, final double lat, final double lon, final double alt) {
        this(UUID.randomUUID().toString(), url, lat, lon, alt);
    }

    public Gift(final String id, final String url, final double lat, final double lon, final double alt) {
        this.id = id;
        this.url = url;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getAlt() {
        return alt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gift)) return false;
        Gift g = (Gift)o;
        return Objects.equal(this.id, g.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(Gift.class)
                .add("id", id)
                .add("url", url)
                .add("lat", lat)
                .add("lon", lon)
                .add("alt", alt)
                .toString();
    }
}
