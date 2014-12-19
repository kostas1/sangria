package com.vintiduo.page.events;

import com.vintiduo.data.Event;

/**
 * Created by kostas on 2014.12.18.
 */
public abstract class ClickListener extends Listener {

    protected abstract void onClick();

    @Override
    public String name() {
        return "click";
    }

    @Override
    public void handle(Event event) {
        if (event.getType().equals(name())) {
            onClick();
        }
    }
}
