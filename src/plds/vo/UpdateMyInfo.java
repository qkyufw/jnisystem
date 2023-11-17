package plds.vo;

import com.dao.impl.VolunteerDaoImpl;
import pojo.Volunteer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMyInfo extends JFrame{
    private JPanel UpdateMyInfo;
    private JTextField pwd1;
    private JTextField phone1;
    private JTextField iphone1;
    private JTextField college1;
    private JTextField kind1;
    private JTextField sex1;
    private JTextField age1;
    private JButton Button;
    private JLabel pwd;
    private JLabel phone;
    private JLabel iphone;
    private JLabel college;
    private JLabel kind;
    private JLabel sex;
    private JLabel age;
    private JLabel head;
    private JButton btn2;

    public UpdateMyInfo(int uid) {
        super("jnisystem");
        setContentPane(UpdateMyInfo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(500,400));

        VolunteerDaoImpl volunteerDao = new VolunteerDaoImpl();
        Volunteer vo = volunteerDao.selectOne(uid);

        head.setText("修改"+vo.getName()+"的个人信息");
        pwd1.setText(vo.getPwd());
        phone1.setText(vo.getPhone());
        iphone1.setText(vo.getIphone());
        college1.setText(vo.getCollege());
        kind1.setText(vo.getKind());
        sex1.setText(String.valueOf(vo.getSex()));
        age1.setText(String.valueOf(vo.getAge()));

        Button.addActionListener(e -> {
            vo.setPwd(pwd1.getText());
            vo.setPhone(phone1.getText());
            vo.setIphone(iphone1.getText());
            vo.setCollege(college1.getText());
            vo.setKind(kind1.getText());
            vo.setSex(Integer.parseInt(sex1.getText()));
            vo.setAge(Integer.parseInt(age1.getText()));
            int update = volunteerDao.update(vo);

            if (update > 0) {
                JOptionPane.showMessageDialog(UpdateMyInfo, "修改成功", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        setVisible(true);
        btn2.addActionListener(e -> {
            int delete = volunteerDao.delete(uid);
            if (delete > 0) {
                JOptionPane.showMessageDialog(UpdateMyInfo, "已注销", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(UpdateMyInfo, "注销失败", "jnisystem", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new UpdateMyInfo(1);
    }
}
