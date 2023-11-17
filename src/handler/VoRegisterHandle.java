package handler;

import com.dao.impl.VolunteerDaoImpl;
import plds.ViRegisterView;
import plds.VoRegisterView;
import pojo.Volunteer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoRegisterHandle extends JFrame implements ActionListener {
    private VoRegisterView voRegisterView;

    public VoRegisterHandle(VoRegisterView voRegisterView) {
        this.voRegisterView = voRegisterView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("注册")) {
            System.out.println("开始注册");
            Volunteer vo = new Volunteer();
            vo.setUid(Integer.parseInt(voRegisterView.getUid1().getText()));
            vo.setName(voRegisterView.getName1().getText());
            vo.setPwd(voRegisterView.getPwd1().getText());
            vo.setPhone(voRegisterView.getPhone1().getText());
            vo.setIphone(voRegisterView.getIphone1().getText());
            vo.setCollege(voRegisterView.getCollege1().getText());
            vo.setKind(voRegisterView.getKind1().getText());
            vo.setAge(Integer.parseInt(voRegisterView.getAge1().getText()));
            vo.setSex(Integer.parseInt(voRegisterView.getSex1().getText()));
            VolunteerDaoImpl volunteerDao = new VolunteerDaoImpl();
            int insert = volunteerDao.insert(vo);
            if (insert > 0) {
                JOptionPane.showMessageDialog(voRegisterView, "success!", "注册成功", JOptionPane.INFORMATION_MESSAGE);
                voRegisterView.dispose();
            } else JOptionPane.showMessageDialog(voRegisterView, "please try again", "注册失败", JOptionPane.WARNING_MESSAGE);
        }
    }
}
