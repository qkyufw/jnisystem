package test;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JframeTest extends JFrame{
    JButton jButton;

    public JframeTest() {
        // 容器组件:jframe, jpanel, jscrollpane 非容器组件:jbutton, jlabel
        super("这是frame的标题");
        setSize(600, 400);  //单位:px
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭退出
        setLocationRelativeTo(null);  //居中
        setResizable(false);


        //设置按钮
        jButton = new JButton("我是按钮");
        Container contentPane = getContentPane();
        contentPane.add(jButton);

        //设置窗体图标
        URL resource = JframeTest.class.getClassLoader().getResource("plds\\heart.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        setVisible(true);
    }

    public static void main(String[] args) {
        new JframeTest();
    }
}
