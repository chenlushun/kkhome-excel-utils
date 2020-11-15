package com.kkhome.excel.text.field;

import javax.swing.*;

public class FromJTextField {

    private static final javax.swing.JTextField jTextField = new javax.swing.JTextField("");

    public static JTextField getJTextField() {
        java.awt.Dimension dm = new java.awt.Dimension(180, 30);
        jTextField.setPreferredSize(dm);
        return jTextField;
    }
}
