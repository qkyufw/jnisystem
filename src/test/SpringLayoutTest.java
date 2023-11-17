package test;

import javax.swing.*;
import java.awt.*;

public class SpringLayoutTest extends JFrame{


    public SpringLayoutTest() {
        super("测试流布局");  //把窗口分开
        Container contentPane = getContentPane();


        setSize(600, 400);  //单位:px
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭退出
        setLocationRelativeTo(null);  //居中
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SpringLayoutTest();
    }
}
