package com.kkhome.excel.panel;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class VersionJPanel extends JPanel {

    public VersionJPanel() {
        // 参考地址： https://blog.csdn.net/qq_36901092/article/details/130075998
        this.setLayout(new GridLayout(3, 5));
        JLabel de = new JLabel("功能说明：\t");
        this.add(de);
        JLabel des = new JLabel("模板文件存储在：D:/template\t");
        this.add(des);
        JLabel label1 = new JLabel("版本：2023/9/20,历史版本在old文件中查看");
        this.add(label1);
    }
}
