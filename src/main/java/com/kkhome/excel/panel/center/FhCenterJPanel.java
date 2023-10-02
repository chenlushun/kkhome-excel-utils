package com.kkhome.excel.panel.center;

import com.kkhome.excel.listener.ExecActionListener;
import com.kkhome.excel.listener.ShangHaiDeliveryAddressHandler;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

@Service
public class FhCenterJPanel extends JPanel {

    public FhCenterJPanel() {
        // 参考地址： https://blog.csdn.net/qq_36901092/article/details/130075998
        this.setLayout(new GridLayout(10, 3));
//        Object[] cols = {"序号", "功能清单"};
//        Object[][] rows = {{"1", "物流单号转换"}, {"2", "上海发货"}, {"3", "vitastudio发货单处理"},{"4", "上新表格生成，"}};
//        JTable jTable = new JTable(rows, cols);
//        jTable.setLayout(new GridLayout(15, 4));
//        this.add(jTable.getTableHeader());
//        this.add(jTable);

        this.add(getButton("物流单号转换"));
//        Vector<String> items = new Vector<>();
//        items.add("默认不加海外仓地址");
//        items.add("加海外仓地址");
//        jPanelCenter.add(new JComboBox(items));

        JCheckBox checkBox1 = new JCheckBox("勾选添加海外仓地址");
        this.add(checkBox1);
        javax.swing.JButton shfh = new javax.swing.JButton("上海发货");
        shfh.addActionListener(actionEvent -> new ShangHaiDeliveryAddressHandler(checkBox1.isSelected()).doHandler());
        this.add(shfh);
        this.add(getButton("vitastudio发货单处理"));
        this.add(getButton("上新表格生成"));
        this.add(getButton("sku合并"));

    }


    private JButton getButton(String text) {
        javax.swing.JButton jbu = new javax.swing.JButton(text);
        jbu.addActionListener(new ExecActionListener());
        return jbu;
    }

    class Editor extends DefaultCellEditor {
        JButton jButton;

        public Editor() {
            super(new JTextField());
            jButton = new JButton("物流单号转换");
            jButton.addActionListener(new ExecActionListener());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return jButton;
        }
    }

    ;
}
