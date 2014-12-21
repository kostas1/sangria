package com.vintiduo.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.util.Date;

public class WebSocketRequest {

    private String page;

    private Event event;

    @JsonSerialize(using = DateSerializer.class)
    private Date created;

    public Event getEvent() {
        return event;
    }

    public String getPage() {
        return page;
    }

    @Override
    public String toString() {
        return "WebSocketRequest{" +
                "page='" + page + '\'' +
                ", event=" + event +
                ", created=" + created +
                '}';
    }
}
