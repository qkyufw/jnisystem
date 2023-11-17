package plds.vista;

import com.dao.impl.VistaDaoImpl;
import plds.vo.VoMainView;
import pojo.Volunteering;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VoingCreate extends JFrame{
    private JTextField name1;
    private JTextField date1;
    private JTextField place1;
    private JTextField describe1;
    private JTextField tap1;
    private JTextField sumOP1;
    private JTextField contact1;
    private JButton button;
    private JLabel name;
    private JLabel date;
    private JLabel place;
    private JLabel describe;
    private JLabel tap;
    private JLabel sumOP;
    private JLabel contact;
    private JPanel createPane;

    public VoingCreate(int vi_id) {
        super("jnisystem");
        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标

        setContentPane(createPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();

        button.addActionListener(e -> {
            Volunteering voing = new Volunteering();
            voing.setName(name1.getText());
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            try {
                voing.setData(ft.parse(date1.getText()));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            voing.setPlace(place1.getText());
            voing.setDescribe(describe1.getText());
            voing.setTap(tap1.getText());
            voing.setVista_id(vi_id);
            voing.setSumOP(Integer.parseInt(sumOP1.getText()));
            voing.setContact(contact1.getText());
            VistaDaoImpl vistaDao = new VistaDaoImpl();
            int number = vistaDao.insertVoing(voing);
            if (number > 0) {
                JOptionPane.showMessageDialog(createPane, "创建成功，活动编号是"+number, "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        setVisible(true);
    }
}
