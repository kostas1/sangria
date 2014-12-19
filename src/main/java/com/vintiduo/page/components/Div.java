package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;

public class Div extends Element {

    Element content;

    public Div() {
    }

    public void setContent(Element content) {
        this.content = content;
    }

    @Override
    public ComponentData getData() {
        return super.getData().put("content", content);
    }
}
