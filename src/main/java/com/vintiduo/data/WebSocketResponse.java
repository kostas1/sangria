package com.vintiduo.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.util.Date;

/**
 * Created by kostas on 2014.12.18.
 */
public class WebSocketResponse {
    private String id;
    private String data;

    @JsonSerialize(using = DateSerializer.class)
    private Date processed;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setProcessed(Date processed) {
        this.processed = processed;
    }

    public Date getProcessed() {
        return processed;
    }
}
