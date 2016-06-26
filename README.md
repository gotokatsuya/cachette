cachette
====

cachette is cache library, implementing multiple cache.

# Supported Cache Type

- memory
- disk

# Usage

## caches

### Multiple cache

```java
public MultipleCache cache = MultipleCache.getInstance();

// enable memory
cache.setupMemoryCache();

// enable disk by context
cache.setupDiskCache(context);

// enable disk by args you like
File cacheDir = ...;
int appVersion = ...;
int maxSize = ...;
cache.setupDiskCache(cacheDir, appVersion, maxSize);

```


### Methods

```java

// get by key.
cache.get("a")

// set value with key.
cache.set("a", "a")

// remove by key.
cache.remove("a")


// remove all cached source. 
cache.removeAll()

```