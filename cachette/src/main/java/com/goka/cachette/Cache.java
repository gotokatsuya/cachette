package com.goka.cachette;

import java.io.IOException;

public interface Cache<T> {
    T get(String key) throws IOException;

    void set(String key, T value) throws IOException;

    void remove(String key) throws IOException;

    void removeAll() throws IOException;
}
