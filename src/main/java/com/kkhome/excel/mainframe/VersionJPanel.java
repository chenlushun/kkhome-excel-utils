package com.kkhome.excel.mainframe;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class VersionJPanel {
    public JPanel getJPanel() {
        // 参考地址： https://blog.csdn.net/qq_36901092/article/details/130075998
        JPanel title = new JPanel();
        title.setLayout(new GridLayout(2, 5));
        JLabel de = new JLabel("功能说明：\t");
        title.add(de);
        JLabel des = new JLabel("模板文件存储在：D:/template\t");
        title.add(des);
        JLabel label1 = new JLabel("版本：2023/9/20,历史版本在old文件中查看");
        title.add(label1);
        return title;
    }
}
