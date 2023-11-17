package plds.vista;

import com.dao.impl.VistaDaoImpl;
import plds.vo.VoMainView;
import pojo.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class UpdateVista extends JFrame{
    private JTextField type1;
    private JTextField name1;
    private JTextField describe1;
    private JTextField name_leader1;
    private JTextField phone_leader1;
    private JTextField id_leader1;
    private JTextField pwd1;
    private JButton submit;
    private JLabel name;
    private JLabel type;
    private JLabel describe;
    private JLabel name_leader;
    private JLabel phone_leader;
    private JLabel id_leader;
    private JLabel college;
    private JLabel pwd;
    private JTextField college1;
    private JPanel updateVista;
    private JLabel head;

    public UpdateVista(int vi_id) {
        super("jnisystem");
        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标

        setContentPane(updateVista);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(500, 400));

        VistaDaoImpl vistaDao = new VistaDaoImpl();
        Vista vista = vistaDao.selectOne(vi_id);
        head.setText("修改"+vista.getName()+"的信息");
        name1.setText(vista.getName());
        type1.setText(vista.getType());
        describe1.setText(vista.getDescribe());
        name_leader1.setText(vista.getName_leader());
        phone_leader1.setText(vista.getPhone_leader());
        id_leader1.setText(String.valueOf(vista.getID_leader()));
        college1.setText(vista.getCollege());
        pwd1.setText(vista.getPwd());

        submit.addActionListener(e -> {
            vista.setName(name1.getText());
            vista.setType(type1.getText());
            vista.setDescribe(describe1.getText());
            vista.setName_leader(name_leader1.getText());
            vista.setPhone_leader(phone_leader1.getText());
            vista.setID_leader(Integer.parseInt(id_leader1.getText()));
            vista.setCollege(college1.getText());
            vista.setPwd(pwd1.getText());

            int update = vistaDao.update(vista);
            if (update > 0) {
                JOptionPane.showMessageDialog(updateVista, "修改成功", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateVista(1);
    }
}
