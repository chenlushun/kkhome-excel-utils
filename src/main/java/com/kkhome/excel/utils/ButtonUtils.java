package com.kkhome.excel.utils;

import javax.swing.*;

public class ButtonUtils {

    public static JButton getEmpty() {
        JButton c = new JButton("          " + "      ");
        c.setContentAreaFilled(false);
        c.setBorderPainted(false);
        c.setEnabled(false);
        return c;
    }
}
