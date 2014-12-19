package com.vintiduo.test;

import com.vintiduo.page.Button;
import com.vintiduo.page.LayoutOrientation;
import com.vintiduo.page.Page;
import com.vintiduo.page.PageResource;
import com.vintiduo.page.events.ClickListener;

/**
 * Created by kostas on 2014.12.18.
 */
@PageResource
public class HomePage extends Page {

    int counter;

    public HomePage() {
        super();
        getLayout().setOrientation(LayoutOrientation.VERTICAL);
        final Button b = new Button("butt", "tekst");
        b.addClickListener(new ClickListener() {
            @Override
            protected void onClick() {
                counter++;
                System.out.println("KLIK");
                b.setText("WOOO" + counter);
            }
        });
        getLayout().addComponent(b);
    }
}
