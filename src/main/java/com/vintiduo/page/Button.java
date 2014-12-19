package com.vintiduo.page;

import com.vintiduo.page.events.ClickListener;

/**
 * Created by kostas on 2014.12.18.
 */
public class Button extends Component {

    private String text;

    public Button(String text) {
        super();
        this.text = text;
    }

    public Button(String name, String text) {
        super(name);
        this.text = text;
    }

    public void addClickListener(ClickListener listener) {
        super.registerListener(listener);
    }

    @Override
    protected TemplateContextBuilder getBuilder() {
        return super.getBuilder().remove("text").put("text", text);
    }

    public void setText(String text) {
        this.text = text;
        refreshComponent();
    }
}
