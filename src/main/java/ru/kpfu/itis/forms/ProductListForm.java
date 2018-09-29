package ru.kpfu.itis.forms;

import ru.kpfu.itis.service.RestProductService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Table of products (view).
 */

public class ProductListForm {

    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;

    public JPanel getPanel() {

        // A panel with a 1x1 grid layout
        panel = new JPanel(new GridLayout(1, 1));

        String[] columns = new String[]{"name", "price", "weight", "manufacturer", "category"};

        String[][] data = RestProductService.getTable(); // get data using repository service

        table = new JTable(data, columns);

        table.getColumnModel().getColumn(0).setPreferredWidth(70);

        for (int i = 1; i < columns.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(50);
        }

        table.setEnabled(false);

        scrollPane = new JScrollPane(table);//для прорутки

        panel.add(scrollPane);
        panel.setBorder(new TitledBorder("Products List"));

        return panel;
    }

}
