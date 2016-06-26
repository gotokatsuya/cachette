package com.goka.cachette;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryCacheTest {

    public MemoryCache<String> cache;

    @Before
    public void setUp() throws Exception {
        cache = MemoryCache.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        cache.removeAll();
    }

    @Test
    public void memoryCacheSet() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
    }

    @Test
    public void memoryCacheGet() throws Exception {
        cache.set("a", "a");
        cache.set("b", "b");
        assertEquals("a", cache.get("a"));
        assertEquals("b", cache.get("b"));
    }

    @Test
    public void memoryCacheRemove() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
        cache.remove("a");
        assertNull(cache.get("a"));
    }

    @Test
    public void memoryCacheRemoveAll() throws Exception {
        cache.set("a", "a");
        assertEquals("a", cache.get("a"));
        cache.removeAll();
        assertNull(cache.get("a"));
    }

}