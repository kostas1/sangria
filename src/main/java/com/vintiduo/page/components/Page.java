package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;
import com.vintiduo.page.log.Logger;

public abstract class Page extends Element {

    protected Logger logger = Logger.forClass(getClass());

    Layout layout;

    public Page() {
        layout = new Layout();
        addElement(layout);
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
