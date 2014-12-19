package com.vintiduo.page;

import org.apache.velocity.VelocityContext;

import java.util.*;

/**
 * Created by kostas on 2014.12.18.
 */
public class Layout extends Component {

    LayoutOrientation orientation;

    public void setOrientation(LayoutOrientation orientation) {
        this.orientation = orientation;
    }

    @Override
    protected TemplateContextBuilder getBuilder() {
        return super.getBuilder().put("class", orientation.name().toLowerCase()).put("items", getComponents());
    }
}
