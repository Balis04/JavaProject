package hu.elte.progtech.jdbc.mysql.examples.macilaci.gui;

import hu.elte.progtech.jdbc.mysql.examples.macilaci.model.MaciLogic;

import javax.swing.*;
import java.awt.*;

public class MaciFrame extends JFrame {

    public MaciFrame(MaciLogic maciLogic) {
        setTitle("MaciLaci");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(new MaciBoard(maciLogic), BorderLayout.CENTER);
        pack();

        setLocationRelativeTo(null);
    }
}
