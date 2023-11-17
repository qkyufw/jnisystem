package plds;

import handler.ViRegisterHandle;

import javax.swing.*;

public class ViRegisterView extends JFrame{
    private JPanel viRegister;
    private JLabel type;
    private JLabel describe;
    private JLabel sumOP;
    private JLabel name_leader;
    private JLabel phone_leader;
    private JLabel name;
    private JLabel id_leader;
    private JLabel college;
    private JLabel pwd;
    private JTextField name1;
    private JTextField type1;
    private JTextField desicribe1;
    private JTextField sumOP1;
    private JTextField name_leader1;
    private JTextField phone_leader1;
    private JTextField id_leader1;
    private JTextField college1;
    private JTextField pwd1;
    private JButton button;

    public ViRegisterView() {
        super("jnisystem");

        setContentPane(viRegister);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        ViRegisterHandle viRegisterHandle = new ViRegisterHandle(this);
        button.addActionListener(viRegisterHandle);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViRegisterView();
    }

    public JTextField getName1() {
        return name1;
    }

    public JTextField getType1() {
        return type1;
    }

    public JTextField getDesicribe1() {
        return desicribe1;
    }

    public JTextField getSumOP1() {
        return sumOP1;
    }

    public JTextField getName_leader1() {
        return name_leader1;
    }

    public JTextField getPhone_leader1() {
        return phone_leader1;
    }

    public JTextField getId_leader1() {
        return id_leader1;
    }

    public JTextField getCollege1() {
        return college1;
    }

    public JTextField getPwd1() {
        return pwd1;
    }
}
