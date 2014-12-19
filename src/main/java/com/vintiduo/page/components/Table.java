package com.vintiduo.page.components;

import com.vintiduo.page.ComponentData;
import com.vintiduo.page.events.TableCellClickListener;

import java.util.ArrayList;
import java.util.List;

public class Table extends Element {

    private String[] header;
    private List<String[]> rows;

    public Table() {
        rows = new ArrayList<>();
    }

    public void addRow(String[] row) {
        rows.add(row);
        refreshElement();
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    @Override
    public ComponentData getData() {
        return super.getData().add("class", "table").put("rows", rows).put("header", header);
    }

    public void addCellClickListener(TableCellClickListener tableCellClickListener) {
        registerListener(tableCellClickListener);
    }
}
