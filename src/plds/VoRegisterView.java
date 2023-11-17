package plds;

import handler.VoRegisterHandle;

import javax.swing.*;

public class VoRegisterView extends JFrame{
    //这里是zhuce界面
    private JTextField uid1;
    private JTextField name1;
    private JTextField pwd1;
    private JTextField phone1;
    private JTextField iphone1;
    private JTextField college1;
    private JTextField kind1;
    private JTextField sex1;
    private JTextField age1;
    private JButton submit;
    private JLabel uid;
    private JLabel name;
    private JLabel pwd;
    private JLabel phone;
    private JLabel iphone;
    private JLabel college;
    private JLabel kind;
    private JLabel sex;
    private JLabel age;
    private JPanel register;
    private JLabel title;

    public VoRegisterView() {
        super("jnisystem");

        setContentPane(register);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        VoRegisterHandle voRegisterHandle = new VoRegisterHandle(this);
        submit.addActionListener(voRegisterHandle);
        setVisible(true);
    }

    public JTextField getUid1() {
        return uid1;
    }

    public JTextField getName1() {
        return name1;
    }

    public JTextField getPwd1() {
        return pwd1;
    }

    public JTextField getPhone1() {
        return phone1;
    }

    public JTextField getIphone1() {
        return iphone1;
    }

    public JTextField getCollege1() {
        return college1;
    }

    public JTextField getKind1() {
        return kind1;
    }

    public JTextField getSex1() {
        return sex1;
    }

    public JTextField getAge1() {
        return age1;
    }
}
