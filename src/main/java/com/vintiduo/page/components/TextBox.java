package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;

public class TextBox extends Element {

    private String text;

    @Override
    public ComponentData getData() {
        return super.getData().put("text", text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
