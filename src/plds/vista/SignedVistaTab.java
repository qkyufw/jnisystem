package plds.vista;

import com.dao.impl.VistaDaoImpl;
import plds.vo.VoMainView;
import pojo.VistaVo;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Vector;

public class SignedVistaTab extends JFrame {
    public SignedVistaTab(int vi_id) {
        super("jnisystem");
        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标

        JPanel signedVistaTab = new JPanel();  //主页面
        JPanel Menu = new JPanel();
        JButton jButton = new JButton("刷新");
        Menu.add(jButton);
        setContentPane(signedVistaTab);
        signedVistaTab.setLayout(new BorderLayout());
        JTable vo = getTeaming(vi_id);
        JScrollPane voingVoJSP = new JScrollPane(vo);  //放表页面
        signedVistaTab.add(voingVoJSP, BorderLayout.CENTER);  //把表页面放入主页面里去
        signedVistaTab.add(Menu, BorderLayout.WEST);

        jButton.addActionListener(e -> reloadTeamMember(vi_id));
        vo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = vo.getSelectedRow();
                new VoYes(vi_id, (Integer) vo.getValueAt(r, 0));
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }

    private JTable getTeaming(int vi_id) {
        //定义vector
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (VistaVo vo : vistaDao.readVo(vi_id)) {
            if (vo.getStatus() == 0) {  //status不为0，则这些是成员
                Vector<Object> hang = new Vector<>();
                hang.addElement(vo.getVo_id());
                hang.addElement(vo.getVo_name());
                hang.addElement(vo.getKind());
                hang.addElement(vo.getSex());
                hang.addElement(vo.getAge());
                hang.addElement(vo.getPhone());
                hang.addElement(vo.getIphone());
                hang.addElement(vo.getCollege());
                data.addElement(hang);
            }
        }

        // tableModel给JTable
        TeamMemberTabModel memberTabModel = TeamMemberTabModel.assembleModel(data);
        JTable jTable = new JTable(memberTabModel);
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));  //字体大小
        tableHeader.setForeground(Color.RED);  //字体颜色
        jTable.setFont(new Font(null, Font.PLAIN, 14));
        jTable.setForeground(Color.black);
        jTable.setRowHeight(30);
        // 设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return jTable;
    }

    //刷新队员表
    private void reloadTeamMember(int vi_id) {  //刷申请队员表
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (VistaVo vo : vistaDao.readVo(vi_id)) {
            if (vo.getStatus() == 0) {
                Vector<Object> hang = new Vector<>();
                hang.addElement(vo.getVo_id());
                hang.addElement(vo.getVo_name());
                hang.addElement(vo.getKind());
                hang.addElement(vo.getSex());
                hang.addElement(vo.getAge());
                hang.addElement(vo.getPhone());
                hang.addElement(vo.getIphone());
                hang.addElement(vo.getCollege());
                data.addElement(hang);
            }
        }
        TeamMemberTabModel.updateModel(data);
    }
}
