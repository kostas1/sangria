package com.vintiduo.data;

import java.util.Map;

public class Event {

    private String source;
    private String type;
    private Map<String, String> data;
    private String path;

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public Map<String, String> getData() {
        return data;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Event{" +
                "source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", data=" + data +
                ", path='" + path + '\'' +
                '}';
    }
}
