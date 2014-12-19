package com.vintiduo.page;

/**
 * Created by kostas on 2014.12.18.
 */
public abstract class Page extends Component {

    Layout layout;

    public Page() {
        layout = new Layout();
        addComponent(layout);
    }

    protected Layout getLayout() {
        return layout;
    }

    @Override
    protected String getTemplateName() {
        return "page";
    }

    @Override
    protected TemplateContextBuilder getBuilder() {
        return super.getBuilder().put("layout", layout);
    }
}
