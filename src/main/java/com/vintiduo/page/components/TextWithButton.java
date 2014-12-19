package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;

public class TextWithButton extends Element {

    public TextWithButton() {

        addComponent(new TextBox());
        addComponent(new Button("click"));
    }

    @Override
    public ComponentData getData() {
        return super.getData().put("content", getDirectChildElements());
    }

    @Override
    public String getTemplateName() {
        return "div";
    }
}
