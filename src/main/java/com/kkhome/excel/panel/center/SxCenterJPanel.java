package com.kkhome.excel.panel.center;

import com.kkhome.excel.listener.ExecActionListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class SxCenterJPanel extends JPanel {
    public SxCenterJPanel() {
        setLayout(null);
        setSize(200, 150);
        setPreferredSize(new Dimension(200, 150));
        // 参考地址： https://blog.csdn.net/qq_36901092/article/details/130075998
        this.setLayout(new GridLayout(8, 2));
        this.add(getButton("物流单号转换"));

    }


    private JButton getButton(String text) {
        javax.swing.JButton jbu = new javax.swing.JButton(text);
        jbu.addActionListener(new ExecActionListener());
        return jbu;
    }

}
