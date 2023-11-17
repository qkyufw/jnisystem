package plds.vista;

import com.dao.impl.VistaDaoImpl;
import plds.vo.VoMainView;

import javax.swing.*;
import java.net.URL;

public class VoYes extends JFrame{
    private JButton yesButton;
    private JButton delButton;
    private JPanel page;

    public VoYes(int vi_id, int vo_id) {
        super("jnisystem");
        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标
        setContentPane(page);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        yesButton.addActionListener(e -> {
            VistaDaoImpl vistaDao = new VistaDaoImpl();
            int add = vistaDao.updateTeam(vi_id, vo_id);
            if (add > 0) {
                JOptionPane.showMessageDialog(page, "已添加为队员", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        setVisible(true);
        delButton.addActionListener(e -> {
            VistaDaoImpl vistaDao = new VistaDaoImpl();
            int delete = vistaDao.deleteTeam(vi_id, vo_id);
            if (delete > 0) {
                JOptionPane.showMessageDialog(page, "已删除", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }
}
