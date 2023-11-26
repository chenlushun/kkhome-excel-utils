package com.kkhome.excel.frame;

import com.kkhome.excel.panel.FooterJPanel;
import com.kkhome.excel.panel.HeaderJPanel;
import com.kkhome.excel.panel.LeftJPanel;
import com.kkhome.excel.panel.VersionJPanel;
import com.kkhome.excel.panel.center.CenterJpanel;
import com.kkhome.excel.panel.center.FhCenterJPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainJframe extends JFrame implements ApplicationRunner {

    private static final long serialVersionUID = -5278598737087831336L;
    private JPanel jPanelSouth;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton, resetButton;

    @Autowired
    private HeaderJPanel headerJPanel;
    @Autowired
    private FooterJPanel footerJPanel;
    @Autowired
    private VersionJPanel versionJPanel;
    @Autowired
    private CenterJpanel centerJpanel;

    public void showUI() {
        //窗体名称
        this.setTitle("可可科技excel助理工具");
        //窗体大小（具体值跟电脑显示器的像素有关，可调整到合适大小）
        this.setSize(600, 600);
//        this.setBounds(100, 100, 700, 540);
        //设置退出进程的方法
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置居中显示用3
        this.setLocationRelativeTo(null);
//        this.setLayout(null);

        //流式布局管理器
        java.awt.BorderLayout flow = new java.awt.BorderLayout();
        //给窗体设置为流式布局——从左到右然后从上到下排列自己写的组件顺序
        this.setLayout(flow);
//        this.setLayout(new GridLayout(4, 2));
//        this.add(new JLabel("3212"));
        //(除了JFrame)其它所有组件设置大小都是该方法
        //文件地址
//        jf.add(FromJTextField.getJTextField());
        Container container = getContentPane();

//        this.add(leftJPanel, BorderLayout.WEST);
        this.add(centerJpanel.getJPanel(), BorderLayout.CENTER);
//        this.add(downLoadImg1688JPanel, BorderLayout.EAST);
//        this.add(versionJPanel, BorderLayout.SOUTH);
//        this.add(jPanelSouth, BorderLayout.SOUTH);
        this.setVisible(true);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.showUI();
    }
}
