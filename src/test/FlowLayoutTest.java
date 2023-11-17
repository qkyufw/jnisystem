package test;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutTest extends JFrame{
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 80, 30));  //默认流布局

    JButton jb1 = new JButton("我是一个测试1");
    JButton jb2 = new JButton("我是一个测试2");
    JButton jb3 = new JButton("我是一个测试3");
    JButton jb4 = new JButton("我是一个测试4");
    JButton jb5 = new JButton("我是一个测试5");
    JButton jb6 = new JButton("我是一个测试6");
    JButton jb7 = new JButton("我是一个测试7");
    JButton jb8 = new JButton("我是一个测试8");
    JButton jb9 = new JButton("我是一个测试9");

    public FlowLayoutTest() {
        super("测试流布局");  //把窗口分开
        Container contentPane = getContentPane();
        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);
        jPanel.add(jb5);
        jPanel.add(jb6);
        jPanel.add(jb7);
        jPanel.add(jb8);
        jPanel.add(jb9);
        contentPane.add(jPanel);

        setSize(600, 400);  //单位:px
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭退出
        setLocationRelativeTo(null);  //居中
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlowLayoutTest();
    }
}
