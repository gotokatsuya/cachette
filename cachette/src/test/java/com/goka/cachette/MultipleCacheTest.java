package com.goka.cachette;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MultipleCacheTest {

    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    public MultipleCache cache;

    @Before
    public void setUp() throws Exception {
        cache = MultipleCache.getInstance();

        // enable memory
        cache.setupMemoryCache();

        // enable disk
        String folderName = "AnyDiskCacheTest";
        File cacheDir = new File(tempDir.getRoot(), folderName);
        if (!cacheDir.exists()) {
            cacheDir = tempDir.newFolder();
        }
        cache.setupDiskCache(cacheDir, 1, Long.MAX_VALUE);
    }

    @After
    public void tearDown() throws Exception {
        cache.removeAll();
    }

    @Test
    public void multipleCacheSet() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
    }

    @Test
    public void multipleCacheGet() throws Exception {
        cache.set("a", "a");
        cache.set("b", "b");
        assertEquals("a", cache.get("a"));
        assertEquals("b", cache.get("b"));
    }

    @Test
    public void multipleCacheRemove() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
        cache.remove("a");
        assertNull(cache.get("a"));
    }

    @Test
    public void multipleCacheRemoveAll() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
        cache.removeAll(); // close and delete all files

        // reopen
        setUp();
        assertNull(cache.get("a"));
    }

}
