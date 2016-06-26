package com.goka.cachette;

import android.support.v4.util.LruCache;

public class MemoryCache<T> implements Cache<T> {

    private static MemoryCache memoryCache;
    private LruCache<String, T> lruCache;

    public synchronized static MemoryCache getInstance() {
        if (memoryCache == null) {
            memoryCache = new MemoryCache();
            int maxMemory = (int) (Runtime.getRuntime().maxMemory());
            int cacheSize = maxMemory / 8;
            if (cacheSize <= 0) {
                cacheSize = 8;
            }
            memoryCache.lruCache = new LruCache<>(cacheSize);
        }
        return memoryCache;
    }


    @Override
    public T get(final String key) {
        return lruCache.get(key);
    }

    @Override
    public void set(final String key, final T value) {
        lruCache.put(key, value);
    }

    @Override
    public void remove(final String key) {
        lruCache.remove(key);
    }

    @Override
    public void removeAll() {
        lruCache.evictAll();
    }
}
