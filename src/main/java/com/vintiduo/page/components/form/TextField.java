package com.vintiduo.page.components.form;

import com.vintiduo.page.ComponentData;
import com.vintiduo.page.components.Element;

public class TextField extends Element {

    private String text;
    private String placeholder;

    @Override
    public ComponentData getData() {
        return super.getData().put("text", text).put("placeholder", placeholder);
    }

    public String getText() {
        return text;
    }

    public TextField text(String text) {
        this.text = text;
        return this;
    }

    public TextField placeholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }
}
