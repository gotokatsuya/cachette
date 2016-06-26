package com.goka.cachette;

import android.app.Application;
import android.test.ApplicationTestCase;

public class MultipleCacheApplicationTest extends ApplicationTestCase<Application> {

    public MultipleCacheApplicationTest() {
        super(Application.class);
    }

    public MultipleCache cache;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        cache = MultipleCache.getInstance();
        cache.setupMemoryCache();
        cache.setupDiskCache(getContext());
    }

    @Override
    public void tearDown() throws Exception {
        cache.removeAll();
        super.tearDown();
    }

    public void testMultipleCacheSet() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
    }

    public void testMultipleCacheGet() throws Exception {
        cache.set("a", "a");
        cache.set("b", "b");
        assertEquals("a", cache.get("a"));
        assertEquals("b", cache.get("b"));
    }

    public void testMultipleCacheRemove() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
        cache.remove("a");
        assertNull(cache.get("a"));
    }

    public void testMultipleCacheRemoveAll() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
        cache.removeAll(); // close and delete all files

        // reopen
        setUp();
        assertNull(cache.get("a"));
    }

}