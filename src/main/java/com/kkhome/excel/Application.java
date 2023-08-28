package com.kkhome.excel;

import com.kkhome.excel.listener.ExecActionListener;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.showUI();
    }

    public void showUI() {
        //窗体类
        javax.swing.JFrame jf = new javax.swing.JFrame();
        //窗体名称
        jf.setTitle("excel工具");
        //窗体大小（具体值跟电脑显示器的像素有关，可调整到合适大小）
        jf.setSize(600, 300);
        //设置退出进程的方法
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置居中显示用3
        jf.setLocationRelativeTo(null);

        //流式布局管理器
        java.awt.FlowLayout flow = new java.awt.FlowLayout();
        //给窗体设置为流式布局——从左到右然后从上到下排列自己写的组件顺序
        jf.setLayout(flow);

        //(除了JFrame)其它所有组件设置大小都是该方法
        //文件地址
        // jf.add(FromJTextField.getJTextField());

        //给窗体添加一个按钮对象
//        jf.add(getButton("佐川地址处理"));
//        jf.add(getButton("Excel合并(KKHOME)"));
//        jf.add(getButton("Excel合并(星川)"));
//        jf.add(getButton("多个excel合并成一个"));
//        jf.add(getButton("文件编码转换"));
        jf.add(getButton("物流单号转换"));
        jf.add(getButton("上海发货"));
        jf.add(getButton("vitastudio发货单处理"));
        //设置可见，放在代码最后一句
        jf.setVisible(true);
    }

    private JButton getButton(String text) {
        javax.swing.JButton jbu = new javax.swing.JButton(text);
        jbu.addActionListener(new ExecActionListener());
        return jbu;
    }
}
