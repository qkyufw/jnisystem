package plds;

import handler.LoginHandler;

import javax.swing.*;

public class LoginView extends JFrame{  //登录界面
    private JPanel test;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton login;
    private JButton register;
    private JLabel id;
    private JLabel pwd;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;

    public LoginView() {
        super("jnisystem");

        setContentPane(test);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        LoginHandler loginHandler = new LoginHandler(this);
        login.addActionListener(loginHandler);
        register.addActionListener(loginHandler);
        setVisible(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        radioButton1.setSelected(true);  //默认志愿者登陆
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public static void main(String[] args) {
        new LoginView();
    }

    public JRadioButton getRadioButton1() {
        return radioButton1;
    }
    public JRadioButton getRadioButton2() {
        return radioButton2;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
