package io.zeddw.gist;

import java.util.Optional;

public interface Config {

    Optional<Object> get(String key);
    
    Object getOrDefault(String key, Object defaultValue);

    void set(String key, Object value);
}
