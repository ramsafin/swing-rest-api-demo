package ru.kpfu.itis.utilities;

import java.awt.*;

public class FormUtilities {

    public static GridBagConstraints getLabelConstraints() {
        GridBagConstraints gb = new GridBagConstraints();
        gb.insets = new Insets(3, 3, 3, 3);
        gb.anchor = GridBagConstraints.BASELINE_TRAILING;
        gb.weightx = 0.0;
        return gb;
    }

    public static GridBagConstraints getTextFieldConstraints() {
        GridBagConstraints gb = new GridBagConstraints();
        gb.insets = new Insets(3, 3, 3, 3);
        gb.anchor = GridBagConstraints.BASELINE;
        gb.weightx = 1.0;
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.gridwidth = GridBagConstraints.REMAINDER;
        return gb;
    }

    public static GridBagConstraints getComboBoxConstraints() {
        GridBagConstraints gb = getTextFieldConstraints();
        gb.anchor = GridBagConstraints.BASELINE;
        return gb;
    }

    public static GridBagConstraints getTabbedPaneConstraints() {
        GridBagConstraints tbpConstraint = new GridBagConstraints();
        tbpConstraint.fill = GridBagConstraints.BOTH;
        tbpConstraint.gridwidth = GridBagConstraints.REMAINDER;
        tbpConstraint.weighty = 2;

        return tbpConstraint;
    }
}
