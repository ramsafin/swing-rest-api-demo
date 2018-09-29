package ru.kpfu.itis;

import ru.kpfu.itis.forms.TabbedPane;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TabbedPane().createGUI());
    }
}
