package com.vintiduo.page.events;

import com.vintiduo.data.Event;

/**
 * Created by kostas on 2014.12.18.
 */
public abstract class Listener {

    public abstract void handle(Event event);
    public abstract String name();
}
