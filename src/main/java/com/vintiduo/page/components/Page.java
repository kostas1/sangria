package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;

public abstract class Page extends Element {

    Layout layout;

    public Page() {
        layout = new Layout();
        addComponent(layout);
    }

    protected Layout getLayout() {
        return layout;
    }

    @Override
    public String getTemplateName() {
        return "page";
    }

    @Override
    public ComponentData getData() {
        return super.getData().put("layout", layout);
    }
}
