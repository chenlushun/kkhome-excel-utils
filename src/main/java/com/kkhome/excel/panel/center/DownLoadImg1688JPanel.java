package com.kkhome.excel.panel.center;

import com.kkhome.excel.frame.MainJframe;
import com.kkhome.excel.panel.AbstractJPanel;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

//@Component
public class DownLoadImg1688JPanel extends AbstractJPanel {


    public DownLoadImg1688JPanel(MainJframe frame) {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);

        JLabel label = new JLabel("1688地址");
        //创建JTextField，16表示16列，用于JTextField的宽度显示而不是限制字符个数
        JTextField textField = new JTextField(15);
        JButton downLoadWin = new JButton("下载窗口图");
        downLoadWin.addActionListener((e) -> {
            String str = textField.getText();//获取输入内容
            //判断是否输入了
            if (str.equals("")) {
                Object[] options = {"OK ", "CANCEL "};
                JOptionPane.showOptionDialog(null, "您还没有输入 ", "提示", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            } else {
                JOptionPane.showMessageDialog(this, "您输入了：" + str);
            }
        });
        JButton downLoadDetail = new JButton("下载详情图");
        downLoadDetail.addActionListener((e) -> {
            String str = textField.getText();//获取输入内容
            //判断是否输入了
            if (str.equals("")) {
                Object[] options = {"OK ", "CANCEL "};
                JOptionPane.showOptionDialog(null, "您还没有输入 ", "提示", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            } else {
                JOptionPane.showMessageDialog(this, "您输入了：" + str);
            }
        });
        addComponent(frame, label, gbl, gbc, 0, 6, 1, 1, 0, 0);
        addComponent(frame, textField, gbl, gbc, 0, 7, 1, 1, 0, 0);
        addComponent(frame, downLoadWin, gbl, gbc, 0, 8, 1, 1, 0, 0);
        addComponent(frame, downLoadDetail, gbl, gbc, 0, 9, 1, 1, 0, 0);


        JLabel tmlabel = new JLabel("天猫地址");
        //创建JTextField，16表示16列，用于JTextField的宽度显示而不是限制字符个数
        JTextField tmtextField = new JTextField(15);
        JButton tmdownLoadWin = new JButton("下载窗口图");
        tmdownLoadWin.addActionListener((e) -> {
            String str = textField.getText();//获取输入内容
            //判断是否输入了
            if (str.equals("")) {
                Object[] options = {"OK ", "CANCEL "};
                JOptionPane.showOptionDialog(null, "您还没有输入 ", "提示", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            } else {
                JOptionPane.showMessageDialog(this, "您输入了：" + str);
            }
        });
        JButton tmdownLoadDetail = new JButton("下载详情图");
        downLoadDetail.addActionListener((e) -> {
            String str = textField.getText();//获取输入内容
            //判断是否输入了
            if (str.equals("")) {
                Object[] options = {"OK ", "CANCEL "};
                JOptionPane.showOptionDialog(null, "您还没有输入 ", "提示", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            } else {
                JOptionPane.showMessageDialog(this, "您输入了：" + str);
            }
        });
        addComponent(frame, tmlabel, gbl, gbc, 0, 10, 1, 1, 0, 0);
        addComponent(frame, tmtextField, gbl, gbc, 0, 11, 1, 1, 0, 0);
        addComponent(frame, tmdownLoadWin, gbl, gbc, 0, 12, 1, 1, 0, 0);
        addComponent(frame, tmdownLoadDetail, gbl, gbc, 0, 13, 1, 1, 0, 0);

        //22.设置frame为最佳大小
        frame.pack();
    }


}
