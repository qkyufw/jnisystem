package handler;

import com.dao.impl.VistaDaoImpl;
import com.dao.impl.VolunteerDaoImpl;
import plds.*;
import plds.admin.AdminMainView;
import plds.vista.ViMainView;
import plds.vo.VoMainView;
import pojo.Vista;
import pojo.Volunteer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHandler extends JFrame implements ActionListener {  //实现登录界面用的
    private LoginView loginView;
    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if (text.equals("登录") && loginView.getRadioButton1().isSelected()) {
            System.out.println("志愿者登录进行中");
            int id = Integer.parseInt(loginView.getTextField1().getText());
            String pwd = String.valueOf(loginView.getPasswordField1().getPassword());
            VolunteerDaoImpl vo = new VolunteerDaoImpl();
            Volunteer volun = vo.login(id, pwd);
            if (volun != null) {
                new VoMainView(volun);
                loginView.dispose();
                System.out.println("登录成功");
            } else JOptionPane.showMessageDialog(loginView, "用户名或密码不匹配", "error", JOptionPane.WARNING_MESSAGE);
        } else if (text.equals("注册") && loginView.getRadioButton1().isSelected()) {
            System.out.println("志愿者注册进行中");
            new VoRegisterView();
        } else if (text.equals("登录") && loginView.getRadioButton2().isSelected()) {
            System.out.println("组织登录进行中");
            if (loginView.getTextField1().getText().equals("admin") && "admin".equals(String.valueOf(loginView.getPasswordField1().getPassword()))) {
                new AdminMainView();
                loginView.dispose();
            }
            int number = Integer.parseInt(loginView.getTextField1().getText());  //服务队编号
            String pwd = String.valueOf(loginView.getPasswordField1().getPassword());  //服务队密码
            VistaDaoImpl vistaDao = new VistaDaoImpl();
            Vista vista = vistaDao.login(number, pwd);  //登录返回一个vista类型
            if (vista != null) {
                new ViMainView(vista);
                loginView.dispose();
                System.out.println("success");
            }else JOptionPane.showMessageDialog(loginView, "用户名或密码不匹配", "error", JOptionPane.WARNING_MESSAGE);
        } else if (text.equals("注册") && loginView.getRadioButton2().isSelected()) {
            System.out.println("组织注册进行中");
            new ViRegisterView();
        }
    }
}
