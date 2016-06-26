package com.goka.cachette;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

public class DiskCache implements Cache<String> {

    private static final int ENTRY_COUNT = 1;
    private static DiskCache diskCache = new DiskCache();
    private DiskLruCache diskLruCache;

    public static DiskCache getInstance() {
        return diskCache;
    }

    public void open(File dir, int appVersion, long maxSize) throws IOException {
        diskLruCache = DiskLruCache.open(dir, appVersion, ENTRY_COUNT, maxSize);
    }

    public void open(Context context) throws IOException, PackageManager.NameNotFoundException {
        File cacheDir = context.getCacheDir();
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
        diskLruCache = DiskLruCache.open(cacheDir, packageInfo.versionCode, ENTRY_COUNT, cacheDir.getFreeSpace());
    }

    @Override
    public String get(final String key) throws IOException {
        DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
        if (snapshot == null) {
            return null;
        }
        return snapshot.getString(0);
    }

    @Override
    public void set(final String key, String value) throws IOException {
        DiskLruCache.Editor editor = diskLruCache.edit(key);
        editor.set(0, value);
        editor.commit();
    }

    @Override
    public void remove(String key) throws IOException {
        diskLruCache.remove(key);
    }

    @Override
    public void removeAll() throws IOException {
        // close and delete all files in cache dir.
        diskLruCache.delete();
    }

    public void flush() throws IOException {
        diskLruCache.flush();
    }

    public void close() throws IOException {
        diskLruCache.close();
    }

}
