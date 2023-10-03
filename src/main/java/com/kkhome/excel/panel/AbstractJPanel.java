package com.kkhome.excel.panel;

import javax.swing.*;
import java.awt.*;

public class AbstractJPanel extends JPanel {

    /**
     * Add_Component函数里的形式参数从左到右依次为
     * JFrame jfr：顶层容器
     * GridBagLayout gbl：网格包布局管理器
     * Component comp：组件，可以接收各种类型的组件对象
     * GridBagConstraints gbc：网格包约束对象
     * 以及以下整型参数
     * gridx, gridy, gridheight, gridwidth, weight_x, weight_y
     * <p>
     * 其中gridx, gridy用来指定组件左上角在网格中的行和列,容器中最左边列的gridx为0，最上边行的gridy为0；
     * gridwidth 和 gridheight用来指定组件显示区域所占的列数和行数，以网格为单元，默认值为 1。
     * gridheight=0时表示该组件是该列的最后一个组件，gridwidth=0时表示该组件是该行的最后一个组件(这一点很重要，之后给界面排版时会用到)
     * weight_x=1时，组件将占满该行的全部空间, weight_y=1时组件将占满该列的所有空间
     */
    public static void addComponent(JFrame jfr, Component comp, GridBagLayout gbl, GridBagConstraints gbc, int gridx, int gridy, int gridheight, int gridwidth, int weight_x, int weight_y) {
        gbc.weightx = weight_x;
        gbc.weighty = weight_y;
        gbc.gridheight = gridheight;
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbl.setConstraints(comp, gbc);
        jfr.add(comp);
    }

}
