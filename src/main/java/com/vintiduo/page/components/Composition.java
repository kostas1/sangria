package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;
import com.vintiduo.page.components.form.FormGroup;

public class Composition extends Element {

    public Element with(Returns<Element> function) {
        addElement(function.get());
        return this;
    }

    @Override
    public ComponentData getData() {
        return super.getData().put("items", getDirectChildElements());
    }

    @Override
    public String getTemplateName() {
        return "composition";
    }

    public Composition and(Element element) {
        Composition composition = new Composition();
        composition.addElement(this, element);
        return composition;
    }

    public Element inside(Composition element) {
        addElement(element);
        return this;
    }
}
