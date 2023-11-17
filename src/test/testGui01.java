package test;

import javax.swing.*;

public class testGui01 {
    private JPanel welcome;
    private JTextField textField1;
    private JLabel uid;
    private JLabel pwd;
    private JButton login;
    private JButton register;


    public static void main(String[] args) {
        JFrame frame = new JFrame("testGui01");
        frame.setContentPane(new testGui01().welcome);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
