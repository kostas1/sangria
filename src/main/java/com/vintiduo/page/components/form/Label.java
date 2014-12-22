package com.vintiduo.page.components.form;

import com.vintiduo.page.ComponentData;
import com.vintiduo.page.components.Element;

public class Label extends Element {

    private String text;
    private String forText;

    public Label(String text) {
        this.text = text;
    }

    public void setFor(String forText) {
        this.forText = forText;
    }

    @Override
    public ComponentData getData() {
        return super.getData().put("text", text).put("for", forText);
    }
}
