package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;

public class Button extends Element {

    private String text;

    public Button(String text) {
        this.text = text;
    }

    @Override
    public ComponentData getData() {
        return super.getData().put("text", text);
    }

    public void setText(String text) {
        this.text = text;
        refreshElement();
    }
}
