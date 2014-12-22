package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;
import com.vintiduo.page.components.form.TextField;

public class TextWithButton extends Element {

    public TextWithButton() {

        addElement(new TextField());
        addElement(new Button("click"));
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
