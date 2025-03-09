package hu.elte.progtech.jdbc.mysql.examples.macilaci;

import hu.elte.progtech.jdbc.mysql.examples.macilaci.gui.MaciFrame;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.model.MaciLogic;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MaciFrame(new MaciLogic()).setVisible(true));
    }
}
