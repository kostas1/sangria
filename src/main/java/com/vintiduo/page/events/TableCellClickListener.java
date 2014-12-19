package com.vintiduo.page.events;

import com.vintiduo.data.Event;

/**
 * Created by kostas on 2014.12.19.
 */
public abstract class TableCellClickListener extends Listener {

    public abstract void onClick(int row, int column);

    @Override
    public void handle(Event event) {
        if (name().equals(event.getType())) {
            if (event.getData().size() > 1) {
                onClick(Integer.parseInt(event.getData().get("row")), Integer.parseInt(event.getData().get("column")));
            } else {
                // todo log error
            }
        }
    }

    @Override
    public String name() {
        return "click";
    }
}
