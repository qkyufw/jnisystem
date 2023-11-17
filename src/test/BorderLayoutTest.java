package test;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutTest extends JFrame{
    JButton northBtn = new JButton("北边的按钮");
    JLabel southLabel = new JLabel("南边的Label");
    JRadioButton westRadioBtn = new JRadioButton("男");
    JTextArea eastArea = new JTextArea("输入内容", 10, 20);
    JButton centerBtn = new JButton("中间的按钮");

    public BorderLayoutTest() {
        super("测试边界类");  //把窗口分开
        Container contentPane = getContentPane();

        //设置布局管理器为borderLayout
        contentPane.setLayout(new BorderLayout());
        contentPane.add(northBtn, BorderLayout.NORTH);
        contentPane.add(southLabel, BorderLayout.SOUTH);
        westRadioBtn.setPreferredSize(new Dimension(200, 0));
        contentPane.add(westRadioBtn, BorderLayout.WEST);
        contentPane.add(eastArea, BorderLayout.EAST);
        contentPane.add(centerBtn, BorderLayout.CENTER);

        setSize(600, 400);  //单位:px
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭退出
        setLocationRelativeTo(null);  //居中
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BorderLayoutTest();
    }
}
