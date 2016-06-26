package com.goka.cachette;

import android.content.Context;
import android.content.pm.PackageManager;

import java.io.File;
import java.io.IOException;

public class MultipleCache implements Cache<String> {

    private static MultipleCache anyCache = new MultipleCache();

    private MemoryCache<String> memoryCache;
    private DiskCache diskCache;

    public static MultipleCache getInstance() {
        return anyCache;
    }

    public void setupMemoryCache() {
        anyCache.memoryCache = MemoryCache.getInstance();
    }

    public void setupDiskCache(File dir, int appVersion, long maxSize) throws IOException {
        anyCache.diskCache = DiskCache.getInstance();
        anyCache.diskCache.open(dir, appVersion, maxSize);
    }

    public void setupDiskCache(Context context) throws IOException, PackageManager.NameNotFoundException {
        anyCache.diskCache = DiskCache.getInstance();
        anyCache.diskCache.open(context);
    }

    @Override
    public String get(String key) throws IOException {
        String value = null;
        if (memoryCache != null) {
            value = memoryCache.get(key);
            if (value != null) {
                return value;
            }
        }
        if (diskCache != null) {
            value = diskCache.get(key);
            if (value != null) {
                return value;
            }
        }
        return value;
    }

    @Override
    public void set(String key, String value) throws IOException {
        if (memoryCache != null) {
            memoryCache.set(key, value);
        }
        if (diskCache != null) {
            diskCache.set(key, value);
        }
    }

    @Override
    public void remove(String key) throws IOException {
        if (memoryCache != null) {
            memoryCache.remove(key);
        }
        if (diskCache != null) {
            diskCache.remove(key);
        }
    }

    @Override
    public void removeAll() throws IOException {
        if (memoryCache != null) {
            memoryCache.removeAll();
        }
        if (diskCache != null) {
            diskCache.removeAll();
        }
    }
}
