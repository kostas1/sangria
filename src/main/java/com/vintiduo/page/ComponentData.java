package com.vintiduo.page;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kostas on 2014.12.19.
 */
public class ComponentData {

    Map<String, Set<Object>> data = new HashMap<>();

    public ComponentData put(String key, Object value) {
        return put(key, value, false);
    }

    public ComponentData add(String key, Object value) {
        return put(key, value, true);
    }

    private ComponentData put(String key, Object value, boolean multiple) {
        Set<Object> list = data.getOrDefault(key, new HashSet<>());
        if (!multiple)
            list.clear();

        list.add(value);
        data.put(key, list);
        return this;
    }

    public Set<Map.Entry<String, Set<Object>>> entrySet() {
        return data.entrySet();
    }
}
