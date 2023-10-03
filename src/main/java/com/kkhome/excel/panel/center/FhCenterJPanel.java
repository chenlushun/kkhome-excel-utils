package com.kkhome.excel.panel.center;

import com.kkhome.excel.frame.MainJframe;
import com.kkhome.excel.listener.ExecActionListener;
import com.kkhome.excel.listener.ShangHaiDeliveryAddressHandler;
import com.kkhome.excel.panel.AbstractJPanel;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

@Service
public class FhCenterJPanel extends AbstractJPanel {

//    @Autowired
//    private MainJframe frame;

    public FhCenterJPanel(MainJframe frame) {
        // 参考地址： https://blog.csdn.net/qq_36901092/article/details/130075998
        this.setLayout(new GridBagLayout());
//        Object[] cols = {"序号", "功能清单"};
//        Object[][] rows = {{"1", "物流单号转换"}, {"2", "上海发货"}, {"3", "vitastudio发货单处理"},{"4", "上新表格生成，"}};
//        JTable jTable = new JTable(rows, cols);
//        jTable.setLayout(new GridLayout(15, 4));
//        this.add(jTable.getTableHeader());
//        this.add(jTable);

        //2.创建GridBagLayout对象
        GridBagLayout gbl = new GridBagLayout();
        frame.setLayout(gbl);

        //4.创建GridBagConstraints对象
        GridBagConstraints gbc = new GridBagConstraints();

//        Vector<String> items = new Vector<>();
//        items.add("默认不加海外仓地址");
//        items.add("加海外仓地址");
//        jPanelCenter.add(new JComboBox(items));


        //9.往frame中添加数组中的前3个Button
        //7.设置所有的GridBagConstraints对象的fill属性为GridBagConstraints.BOTH,当有空白区域时，组件自动扩大占满空白区域
        gbc.fill = GridBagConstraints.BOTH;
        addComponent(frame, getButton("物流单号转换"), gbl, gbc, 0, 0, 1, 4, 0, 0);
        //11.把button数组中第四个按钮添加到frame中
        JCheckBox checkBox1 = new JCheckBox("勾选添加海外仓地址");
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        javax.swing.JButton shfh = new javax.swing.JButton("上海发货");
        shfh.addActionListener(actionEvent -> new ShangHaiDeliveryAddressHandler(checkBox1.isSelected()).doHandler());
        addComponent(frame, checkBox1, gbl, gbc, 0, 1, 1, 2, 1, 0);
        addComponent(frame, shfh, gbl, gbc, 2, 1, 1, 2, 0, 0);

        gbc.gridheight = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        //14.把GridBagConstaints的gridheight和gridwidth设置为2，表示纵向和横向会占用两个网格
        addComponent(frame, getButton("vitastudio发货单处理"), gbl, gbc, 0, 3, 1, 4, 0, 0);
        addComponent(frame, getButton("上新表格生成"), gbl, gbc, 0, 4, 1, 4, 0, 0);
        addComponent(frame, getButton("sku合并"), gbl, gbc, 0, 5, 1 ,4, 1, 0);
        addComponent(frame, getButton("1688图片下载"), gbl, gbc, 0, 6, 1 ,4, 1, 0);


        JLabel label = new JLabel("1688商品地址");
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
        addComponent(frame, label, gbl, gbc, 0, 9, 1, 1, 0, 0);
        addComponent(frame, textField, gbl, gbc, 1, 9, 1, 1, 0, 0);
        addComponent(frame, downLoadWin, gbl, gbc, 2, 9, 1, 1, 0, 0);
        addComponent(frame, downLoadDetail, gbl, gbc, 3, 9, 1, 1, 0, 0);


        JLabel tmlabel = new JLabel("天猫商品地址");
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
        addComponent(frame, tmtextField, gbl, gbc, 1, 10, 1, 1, 0, 0);
        addComponent(frame, tmdownLoadWin, gbl, gbc, 2, 10, 1, 1, 0, 0);
        addComponent(frame, tmdownLoadDetail, gbl, gbc, 3, 10, 1, 1, 0, 0);
        //22.设置frame为最佳大小
        frame.pack();
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
}
