package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;

public class Layout extends Element {

    LayoutOrientation orientation;

    public void setOrientation(LayoutOrientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public ComponentData getData() {
        return super.getData().add("class", orientation.name().toLowerCase()).put("items", getDirectChildElements());
    }

    public void addElement(Element element) {
        super.addElement(element);
    }
}
