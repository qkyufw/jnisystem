package plds.admin;

import com.dao.AdminDao;
import com.dao.impl.AdminDaoImpl;
import com.dao.impl.VistaDaoImpl;
import plds.vo.VoMainView;
import pojo.Vista;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Vector;

public class NewVista extends JFrame {
    public NewVista() {
        super("jnisystem");
        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标

        JPanel jPanel = new JPanel();  //主页面
        JPanel Menu = new JPanel();
        JButton jButton = new JButton("刷新");
        Menu.add(jButton);
        setContentPane(jPanel);
        jPanel.setLayout(new BorderLayout());
        JTable jTable = getVistaTa();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jPanel.add(jScrollPane, BorderLayout.CENTER);
        jPanel.add(Menu, BorderLayout.WEST);
        Menu.setPreferredSize(new Dimension(120, 1080));
        jButton.setPreferredSize(new Dimension(120, 40));
        jButton.addActionListener(e -> reVistaTa());

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = jTable.getSelectedRow();
                int i = JOptionPane.showConfirmDialog(jPanel, "是否添加为服务队", "jnisystem", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    AdminDaoImpl adminDao = new AdminDaoImpl();
                    int i1 = adminDao.addVista((Integer) jTable.getValueAt(r, 0));
                    if (i1 > 0) JOptionPane.showMessageDialog(jPanel, "添加成功", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(jPanel,"添加失败", "jnisystem", JOptionPane.WARNING_MESSAGE);
                } else {
                    AdminDaoImpl adminDao = new AdminDaoImpl();
                    int i1 = adminDao.deleteVista((Integer) jTable.getValueAt(r, 0));
                    if (i1 > 0) JOptionPane.showMessageDialog(jPanel, "已成功", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(jPanel,"删除失败", "jnisystem", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }

    private JTable getVistaTa() {
        Vector<Vector<Object>> data = new Vector<>();
        AdminDaoImpl adminDao = new AdminDaoImpl();
        for (Vista vista : adminDao.selectAllVista()) {
            if (vista.getStatus() == 0) {
                Vector<Object> hang = new Vector<>();
                hang.addElement(vista.getNumber());
                hang.addElement(vista.getName());
                hang.addElement(vista.getType());
                hang.addElement(vista.getDescribe());
                hang.addElement(vista.getSumOP());
                hang.addElement(vista.getName_leader());
                hang.addElement(vista.getPhone_leader());
                hang.addElement(vista.getID_leader());
                hang.addElement(vista.getCollege());
                hang.addElement(vista.getPwd());
                data.add(hang);
            }
        }
        VistaTabModel vistaTabModel = VistaTabModel.assembleModel(data);
        JTable jTable = new JTable(vistaTabModel);
        type(jTable);
        return jTable;
    }

    private void reVistaTa() {
        Vector<Vector<Object>> data = new Vector<>();
        AdminDaoImpl adminDao = new AdminDaoImpl();
        for (Vista vista : adminDao.selectAllVista()) {
            if (vista.getStatus() == 0) {
                Vector<Object> hang = new Vector<>();
                hang.addElement(vista.getNumber());
                hang.addElement(vista.getName());
                hang.addElement(vista.getType());
                hang.addElement(vista.getDescribe());
                hang.addElement(vista.getSumOP());
                hang.addElement(vista.getName_leader());
                hang.addElement(vista.getPhone_leader());
                hang.addElement(vista.getID_leader());
                hang.addElement(vista.getCollege());
                hang.addElement(vista.getPwd());
                data.add(hang);
            }
        }
        VistaTabModel.assembleModel(data);
    }

    private void type(JTable jTable) {  //设置表格格式
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));  //字体大小
        tableHeader.setForeground(Color.RED);  //字体颜色
        jTable.setFont(new Font(null, Font.PLAIN, 14));
        jTable.setForeground(Color.black);
        jTable.setRowHeight(30);
        // 设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public static void main(String[] args) {
        new NewVista();
    }
}
