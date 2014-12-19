package com.vintiduo.data;

import java.io.Serializable;

/**
 * Created by kostas on 2014.12.18.
 */
public class Event {

    private String source;
    private String type;
    private String data;

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getData() {
        return data;
    }
}
