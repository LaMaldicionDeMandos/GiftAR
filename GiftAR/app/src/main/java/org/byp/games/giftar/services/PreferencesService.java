package org.byp.games.giftar.services;

/**
 * Created by boot on 9/24/15.
 */
public interface PreferencesService {
    <T> T get(String key, Class<T> clazz);
    <T> void put(String key, T value);
    boolean contain(String key);
    void delete(String key);
}
