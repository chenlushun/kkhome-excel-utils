package com.kkhome.excel.panel;

import com.kkhome.excel.frame.MainJframe;
import com.kkhome.excel.panel.center.FhCenterJPanel;
import com.kkhome.excel.panel.center.SxCenterJPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class LeftJPanel extends JPanel {

    @Autowired
    private MainJframe mainJframe;
    @Autowired
    private FhCenterJPanel fhCenterJPanel;
    @Autowired
    private SxCenterJPanel sxCenterJPanel;

    public LeftJPanel() {
        this.setLayout(new GridLayout(10, 2));
        final JPopupMenu popupMenu = new JPopupMenu();

        javax.swing.JMenuItem sx = new javax.swing.JMenuItem("上新");
        javax.swing.JMenuItem fh = new javax.swing.JMenuItem("发货");
        fh.addActionListener(e -> {
            System.out.println("123");
            fh.setSelected(true);
            sx.setSelected(false);
            fhCenterJPanel.setEnabled(false);

        });
        this.add(fh);

        sx.addActionListener(e -> {
            System.out.println("123123");
            fh.setSelected(false);
            sx.setSelected(true);

        });
        popupMenu.add(sx);
        popupMenu.add(fh);

        this.add(popupMenu);
    }
}
