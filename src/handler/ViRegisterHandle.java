package handler;

import com.dao.impl.VistaDaoImpl;
import plds.ViRegisterView;
import pojo.Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViRegisterHandle extends JFrame implements ActionListener {
    private ViRegisterView viRegisterView;
    public ViRegisterHandle(ViRegisterView viRegisterView) {
        this.viRegisterView = viRegisterView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("确认注册")) {
            System.out.println("开始注册服务队");
            Vista vista = new Vista();
            vista.setName(viRegisterView.getName1().getText());
            vista.setType(viRegisterView.getType1().getText());
            vista.setDescribe(viRegisterView.getDesicribe1().getText());
            vista.setSumOP(Integer.parseInt(viRegisterView.getSumOP1().getText()));
            vista.setName_leader(viRegisterView.getName_leader1().getText());
            vista.setPhone_leader(viRegisterView.getPhone_leader1().getText());
            vista.setID_leader(Integer.parseInt(viRegisterView.getId_leader1().getText()));
            vista.setCollege(viRegisterView.getCollege1().getText());
            vista.setPwd(viRegisterView.getPwd1().getText());
            VistaDaoImpl vistaDao = new VistaDaoImpl();
            int number = vistaDao.sign(vista);
            if (number != 0) {
                JOptionPane.showMessageDialog(viRegisterView, "您的编号是"+number+"请等待管理员审核通过", "已注册", JOptionPane.INFORMATION_MESSAGE);
                viRegisterView.dispose();
            } else JOptionPane.showMessageDialog(viRegisterView, "please try again", "注册失败", JOptionPane.WARNING_MESSAGE);
        }
    }
}
