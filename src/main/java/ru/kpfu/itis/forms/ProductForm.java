package ru.kpfu.itis.forms;

import ru.kpfu.itis.dto.Manufacturer;
import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.service.RestProductService;
import ru.kpfu.itis.utilities.FormUtilities;
import ru.kpfu.itis.utilities.ManufacturerUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Product form - new product creation.
 */

public class ProductForm {

    private static final String EMPTY_STRING = "";

    private JPanel panel; // the main product creation panel

    // Product name
    private JLabel nameLabel;
    private JTextField name;

    // Product price
    private JLabel priceLabel;
    private JTextField price;

    // Product weight
    private JLabel weightLabel;
    private JTextField weight;

    // Product manufacturer
    private JLabel manufacturerLabel;
    private JComboBox<Object> man;

    private JLabel categoryLabel;
    private JTextField category;

    private JLabel infoLabel;
    private JButton addButton;


    public JPanel getPanel() {

        //JPanel with a grid bag layout
        panel = new JPanel(new GridBagLayout());

        nameLabel = new JLabel("name", JLabel.LEFT);
        name = new JTextField(EMPTY_STRING, 45);

        panel.add(nameLabel, FormUtilities.getLabelConstraints());
        panel.add(name, FormUtilities.getTextFieldConstraints());

        priceLabel = new JLabel("price", JLabel.LEFT);
        price = new JTextField(EMPTY_STRING, 32);

        panel.add(priceLabel, FormUtilities.getLabelConstraints());
        panel.add(price, FormUtilities.getTextFieldConstraints());


        weightLabel = new JLabel("weight", JLabel.LEFT);
        weight = new JTextField(EMPTY_STRING, 10);

        panel.add(weightLabel, FormUtilities.getLabelConstraints());
        panel.add(weight, FormUtilities.getTextFieldConstraints());

        manufacturerLabel = new JLabel("manufacturer", JLabel.LEFT);
        man = new JComboBox<>(Arrays.stream(Manufacturer.values()).map(Manufacturer::getCountry).toArray());
        man.setEditable(false);

        panel.add(manufacturerLabel, FormUtilities.getLabelConstraints());
        panel.add(man, FormUtilities.getComboBoxConstraints());

        categoryLabel = new JLabel("category", JLabel.LEFT);
        category = new JTextField(EMPTY_STRING, 25);

        panel.add(categoryLabel, FormUtilities.getLabelConstraints());
        panel.add(category, FormUtilities.getTextFieldConstraints());

        infoLabel = new JLabel(EMPTY_STRING, JLabel.CENTER);

        // Spacer
        GridBagConstraints spacer = new GridBagConstraints();
        spacer.fill = GridBagConstraints.BOTH;
        spacer.gridwidth = GridBagConstraints.REMAINDER;
        spacer.weighty = 1.0;

        panel.add(infoLabel, spacer);

        addButton = new JButton("Add");
        panel.add(addButton, FormUtilities.getTextFieldConstraints());

        addButton.addActionListener(getAddActionListener());

        panel.setBorder(new TitledBorder("Product"));

        return panel;
    }

    private ActionListener getAddActionListener() {
        return e -> {
            String productName = name.getText();
            String productPrice = price.getText();
            String productWeight = weight.getText();
            String productManufacturer = (String) man.getSelectedItem();
            String productCategory = category.getText();

            try {

                Manufacturer manufacturer = ManufacturerUtils.getByCountry(productManufacturer);
                assert (manufacturer != null);

                RestProductService.add(new ProductDto(
                        productName,
                        Double.parseDouble(productPrice),
                        Double.parseDouble(productWeight),
                        manufacturer,
                        productCategory));

                // reset fields
                name.setText(EMPTY_STRING);
                price.setText(EMPTY_STRING);
                weight.setText(EMPTY_STRING);
                category.setText(EMPTY_STRING);

                // inform user that new product was added
                infoLabel.setText("Product has been successfully added!");

                // FIXME: handle parsing exceptions separately
            } catch (Exception ex) {
                ex.printStackTrace();
                infoLabel.setText("Not valid input data : " + ex.getMessage());
                JOptionPane.showMessageDialog(panel, "Please, try to add the product later",
                        "Something went wrong!", JOptionPane.ERROR_MESSAGE);
            }
        };
    }
}