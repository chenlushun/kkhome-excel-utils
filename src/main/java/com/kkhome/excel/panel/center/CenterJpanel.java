package com.kkhome.excel.panel.center;

import com.kkhome.excel.listener.ExecActionListener;
import com.kkhome.excel.listener.YnDeliveryAddressHandler;
import com.kkhome.excel.listener.YnHmHandler;
import com.kkhome.excel.listener.YnHmThHandler;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class CenterJpanel {
    public JPanel getJPanel() {
        // 参考地址： https://blog.csdn.net/qq_36901092/article/details/130075998
        JPanel entcer = new JPanel();
        entcer.setLayout(new GridLayout(10, 2));
        entcer.add(getButton("物流单号转换"));
//        entcer.add(ButtonUtils.getEmpty());

//        Vector<String> items = new Vector<>();
//        items.add("默认不加海外仓地址");
//        items.add("加海外仓地址");
//        jPanelCenter.add(new JComboBox(items));

        JCheckBox checkBox1 = new JCheckBox("勾选添加海外仓地址");
        entcer.add(checkBox1);
//        javax.swing.JButton shfh = new javax.swing.JButton("宋文勇发货");
//        shfh.addActionListener(actionEvent -> new ShangHaiDeliveryAddressHandler(checkBox1.isSelected()).doHandler());
//        entcer.add(shfh);


        javax.swing.JButton ynzc = new javax.swing.JButton("亚诺-佐川小包");
        ynzc.addActionListener(actionEvent -> new YnDeliveryAddressHandler(checkBox1.isSelected()).doHandler());
        entcer.add(ynzc);


        javax.swing.JButton ynhm = new javax.swing.JButton("亚诺-黑猫小包");
        ynhm.addActionListener(actionEvent -> new YnHmHandler(checkBox1.isSelected()).doHandler());
        entcer.add(ynhm);

        javax.swing.JButton ynhmth = new javax.swing.JButton("亚诺-黑猫投函");
        ynhmth.addActionListener(actionEvent -> new YnHmThHandler(checkBox1.isSelected()).doHandler());
        entcer.add(ynhmth);

//        entcer.add(ButtonUtils.getEmpty());
        entcer.add(getButton("vitastudio发货单处理"));

        entcer.add(getButton("上新表格生成"));
        entcer.add(getButton("sku合并"));
        entcer.add(getButton("1688价格读取"));
        return entcer;
    }


    private JButton getButton(String text) {
        javax.swing.JButton jbu = new javax.swing.JButton(text);
        jbu.addActionListener(new ExecActionListener());
        return jbu;
    }
}
