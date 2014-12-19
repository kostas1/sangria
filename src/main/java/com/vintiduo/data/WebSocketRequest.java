package com.vintiduo.data;

import java.io.Serializable;

/**
 * Created by kostas on 2014.12.18.
 */
public class WebSocketRequest {

    private String page;

    private Event event;

    public Event getEvent() {
        return event;
    }

    public String getPage() {
        return page;
    }
}
