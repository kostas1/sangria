package com.vintiduo.page.components.form;

import com.vintiduo.page.ComponentData;
import com.vintiduo.page.components.Composition;

public class FormGroup extends Composition {

    public FormGroup(Label label, TextField textField) {
        label.setFor(textField.getId());
        addElement(label, textField);
    }

    @Override
    public ComponentData getData() {
        return super.getData().put("items", getDirectChildElements());
    }
}
