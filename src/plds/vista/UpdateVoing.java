package plds.vista;

import com.dao.impl.VistaDaoImpl;
import plds.vo.VoMainView;
import pojo.Volunteering;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateVoing extends JFrame{
    private JTextField name1;
    private JTextField date1;
    private JTextField palce1;
    private JTextField describe1;
    private JTextField tap1;
    private JTextField sumOP1;
    private JTextField contact1;
    private JButton submit;
    private JLabel name;
    private JLabel date;
    private JLabel place;
    private JLabel describe;
    private JLabel tap;
    private JLabel sumOP;
    private JLabel contact;
    private JPanel updateVoing;
    private JLabel head;
    private JButton delete;
    private JButton read;

    public UpdateVoing(int voing_id) {  //根据活动编号，修改活动内容
        super("jnisystem");
        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标

        setContentPane(updateVoing);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(500, 400));

        VistaDaoImpl vistaDao = new VistaDaoImpl();
        Volunteering voing = vistaDao.selectOneVoing(voing_id);

        head.setText("修改活动："+voing_id);
        name1.setText(voing.getName());
        date1.setText(String.valueOf(voing.getDate()));
        palce1.setText(voing.getPlace());
        describe1.setText(voing.getDescribe());
        tap1.setText(voing.getTap());
        sumOP1.setText(String.valueOf(voing.getSumOP()));
        contact1.setText(voing.getContact());

        submit.addActionListener(e -> {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            voing.setName(name1.getText());
            try {
                voing.setData(ft.parse(date1.getText()));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            voing.setPlace(palce1.getText());
            voing.setDescribe(describe1.getText());
            voing.setTap(tap1.getText());
            voing.setSumOP(Integer.parseInt(sumOP1.getText()));
            voing.setContact(contact1.getText());

            int update = vistaDao.updateVoing(voing);
            if (update > 0) {
                JOptionPane.showMessageDialog(updateVoing, "修改成功", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        setVisible(true);
        delete.addActionListener(e -> {
            int delete = vistaDao.deleteVoing(voing_id);
            if (delete > 0) {
                JOptionPane.showMessageDialog(updateVoing, "已删除", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        read.addActionListener(e -> new VoingVo(voing_id));
    }

    public static void main(String[] args) {
        new UpdateVoing(4);
    }
}
