package com.vintiduo.test;

import com.vintiduo.page.components.*;
import com.vintiduo.page.PageResource;
import com.vintiduo.page.components.form.Form;
import com.vintiduo.page.components.form.FormGroup;
import com.vintiduo.page.components.form.Label;
import com.vintiduo.page.components.form.TextField;
import com.vintiduo.page.events.ClickListener;
import com.vintiduo.page.events.TableCellClickListener;

/**
 * Created by kostas on 2014.12.18.
 */
@PageResource
public class HomePage extends Page {

    int counter;

    public HomePage() {
        getLayout().setOrientation(LayoutOrientation.VERTICAL);
        final Button b = new Button("tekst");
        b.addClickListener(new ClickListener() {
            @Override
            protected void onClick() {
                counter++;
                logger.info("onClick", "KLIK");
                b.setText("WOOO" + counter);
            }
        });
        final Table table = new Table();
        table.setHeader(new String[] { "a", "b", "c" });
        table.addRow(new String[] {"1", "2", "3"});
        table.addRow(new String[] { "4", "5", "6" });
        table.addCellClickListener(new TableCellClickListener() {
            @Override
            public void onClick(int row, int column) {
                logger.info("onClick", "clicked", "row", row, "column", column);
                table.addRow(new String[]{"7", "8", "9"});
            }
        });
        getLayout().addElement(table);
        getLayout().addElement(b);

        getLayout().addElement(new Form().inside(
                     new FormGroup(new Label("label1"), new TextField().placeholder("pl1"))
                .and(new FormGroup(new Label("label2"), new TextField().placeholder("pl2")))
                .and(new Button("submit"))));

    }
}
