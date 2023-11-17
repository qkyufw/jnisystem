package plds.admin;

import com.dao.AdminDao;
import com.dao.impl.AdminDaoImpl;
import plds.vista.UpdateVista;
import plds.vista.UpdateVoing;
import plds.vo.UpdateMyInfo;
import plds.vo.VoMainView;
import pojo.Vista;
import pojo.Volunteer;
import pojo.Volunteering;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Vector;

public class AdminMainView extends JFrame {
    public AdminMainView() {
        super("jnisystem");
        URL imgUrl = AdminMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标
        JTabbedPane tabbedPane = new JTabbedPane();  //初始化主页面
        setContentPane(tabbedPane);  //选择主页面

        //初始化表单
        JTable voTab = getVoTa();
        JTable vistaTab = getVistaTa();
        JTable voingTab = getVoingTa();

        //标签
        JPanel voTap = new JPanel();
        JPanel vistaTap = new JPanel();
        JPanel voingTap = new JPanel();

        voTap.setLayout(new BorderLayout());
        vistaTap.setLayout(new BorderLayout());
        voingTap.setLayout(new BorderLayout());

        //初始化并导入表单
        JScrollPane voJSP = new JScrollPane(voTab);
        JScrollPane vistaJSP = new JScrollPane(vistaTab);
        JScrollPane voingJSP = new JScrollPane(voingTab);

        //初始化菜单
        JPanel voMenu = new JPanel();
        JPanel vistaMenu = new JPanel();
        JPanel voingMenu = new JPanel();

        //设置菜单布局
        voMenu.setLayout(new FlowLayout());
        vistaMenu.setLayout(new FlowLayout());
        voingMenu.setLayout(new FlowLayout());

        //初始化按钮并给名字
        JButton F5vo = new JButton("刷新");
        JButton F5vista = new JButton("刷新");
        JButton jButton = new JButton("新申请");
        JButton F5voing = new JButton("刷新");

        //设置按钮大小
        F5vo.setPreferredSize(new Dimension(120, 40));
        F5vista.setPreferredSize(new Dimension(120, 40));
        jButton.setPreferredSize(new Dimension(120, 40));
        F5voing.setPreferredSize(new Dimension(120, 40));

        //放置按钮
        voMenu.add(F5vo);
        vistaMenu.add(F5vista);
        vistaMenu.add(jButton);
        voingMenu.add(F5voing);

        //设置布局
        voTap.add(voMenu, BorderLayout.WEST);
        voTap.add(voJSP, BorderLayout.CENTER);
        vistaTap.add(vistaMenu, BorderLayout.WEST);
        vistaTap.add(vistaJSP, BorderLayout.CENTER);
        voingTap.add(voingMenu, BorderLayout.WEST);
        voingTap.add(voingJSP, BorderLayout.CENTER);

        //设置菜单布局大小
        voMenu.setPreferredSize(new Dimension(120, 1080));
        vistaMenu.setPreferredSize(new Dimension(120, 1080));
        voingMenu.setPreferredSize(new Dimension(120, 1080));

        //添加标签
        tabbedPane.addTab("志愿者", voTap);
        tabbedPane.addTab("服务队", vistaTap);
        tabbedPane.addTab("志愿活动", voingTap);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        //监听器
        F5vo.addActionListener(e -> reVoTa());
        F5vista.addActionListener(e -> reVistaTa());
        jButton.addActionListener(e -> new NewVista());
        F5voing.addActionListener(e -> reVoingTa());
        voTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UpdateMyInfo((Integer) voTab.getValueAt(voTab.getSelectedRow(), 0));
            }
        });
        vistaTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UpdateVista((Integer) vistaTab.getValueAt(vistaTab.getSelectedRow(), 0));
            }
        });
        voingTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UpdateVoing((Integer) voingTab.getValueAt(voingTab.getSelectedRow(), 0));
            }
        });
    }

    private JTable getVoTa() {
        Vector<Vector<Object>> data = new Vector<>();
        AdminDaoImpl adminDao = new AdminDaoImpl();
        for (Volunteer vo : adminDao.selectAllVo()) {
            Vector<Object> hang = new Vector<>();
            hang.addElement(vo.getUid());
            hang.addElement(vo.getName());
            hang.addElement(vo.getPwd());
            hang.addElement(vo.getPhone());
            hang.addElement(vo.getIphone());
            hang.addElement(vo.getCollege());
            hang.addElement(vo.getKind());
            hang.addElement(vo.getSex());
            hang.addElement(vo.getAge());
            data.add(hang);
        }
        VolunteerTabModel volunteerTabModel = VolunteerTabModel.assembleModel(data);
        JTable jTable = new JTable(volunteerTabModel);
        type(jTable);
        return jTable;
    }

    private void reVoTa() {
        Vector<Vector<Object>> data = new Vector<>();
        AdminDaoImpl adminDao = new AdminDaoImpl();
        for (Volunteer vo : adminDao.selectAllVo()) {
            Vector<Object> hang = new Vector<>();
            hang.addElement(vo.getUid());
            hang.addElement(vo.getName());
            hang.addElement(vo.getPwd());
            hang.addElement(vo.getPhone());
            hang.addElement(vo.getIphone());
            hang.addElement(vo.getCollege());
            hang.addElement(vo.getKind());
            hang.addElement(vo.getSex());
            hang.addElement(vo.getAge());
            data.add(hang);
        }
        VolunteerTabModel.assembleModel(data);
    }

    private JTable getVistaTa() {
        Vector<Vector<Object>> data = new Vector<>();
        AdminDaoImpl adminDao = new AdminDaoImpl();
        for (Vista vista : adminDao.selectAllVista()) {
            if (vista.getStatus() != 0) {
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
            if (vista.getStatus() != 0) {
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

    private JTable getVoingTa() {
        Vector<Vector<Object>> data = new Vector<>();
        AdminDaoImpl adminDao = new AdminDaoImpl();
        for (Volunteering voing : adminDao.selectAllVoing()) {
            Vector<Object> hang = new Vector<>();
            hang.addElement(voing.getNumber());
            hang.addElement(voing.getName());
            hang.addElement(voing.getDate());
            hang.addElement(voing.getPlace());
            hang.addElement(voing.getTap());
            hang.addElement(voing.getVista_id());
            hang.addElement(voing.getSumOP());
            hang.addElement(voing.getContact());
            hang.addElement(voing.getDescribe());
            data.add(hang);
        }
        VoingTabModel voingTabModel = VoingTabModel.assembleModel(data);
        JTable jTable = new JTable(voingTabModel);
        type(jTable);
        return jTable;
    }

    private void reVoingTa() {
        Vector<Vector<Object>> data = new Vector<>();
        AdminDaoImpl adminDao = new AdminDaoImpl();
        for (Volunteering voing : adminDao.selectAllVoing()) {
            Vector<Object> hang = new Vector<>();
            hang.addElement(voing.getNumber());
            hang.addElement(voing.getName());
            hang.addElement(voing.getDate());
            hang.addElement(voing.getPlace());
            hang.addElement(voing.getTap());
            hang.addElement(voing.getVista_id());
            hang.addElement(voing.getSumOP());
            hang.addElement(voing.getContact());
            hang.addElement(voing.getDescribe());
            data.add(hang);
        }
        VoingTabModel.assembleModel(data);
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
        new AdminMainView();
    }
}

class VolunteerTabModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("志愿者学号");
        columns.addElement("志愿者姓名");
        columns.addElement("志愿者密码");
        columns.addElement("志愿者电话");
        columns.addElement("i志愿绑定号码");
        columns.addElement("学院");
        columns.addElement("类别");
        columns.addElement("性别");
        columns.addElement("年龄");
    }

    private VolunteerTabModel() {
        super(null, columns);
    }

    private static VolunteerTabModel volunteerTabModel = new VolunteerTabModel();

    public static VolunteerTabModel assembleModel(Vector<Vector<Object>> data) {
        volunteerTabModel.setDataVector(data, columns);
        return volunteerTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {
        volunteerTabModel.setDataVector(data, columns);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

class VistaTabModel extends DefaultTableModel {  //查看服务队的模型
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("服务队编号");
        columns.addElement("服务队名称");
        columns.addElement("服务队类型");
        columns.addElement("服务队描述");
        columns.addElement("服务队人数");
        columns.addElement("队长姓名");
        columns.addElement("队长电话");
        columns.addElement("队长学号");
        columns.addElement("归属学院");
        columns.addElement("密码");
    }

    private VistaTabModel() {
        super(null, columns);
    }

    private static VistaTabModel vistaTabModel = new VistaTabModel();

    public static VistaTabModel assembleModel(Vector<Vector<Object>> data) {
        vistaTabModel.setDataVector(data, columns);
        return vistaTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {
        vistaTabModel.setDataVector(data, columns);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

class VoingTabModel extends DefaultTableModel {  //查看服务队的模型
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("活动编号");
        columns.addElement("活动名称");
        columns.addElement("活动日期");
        columns.addElement("活动地点");
        columns.addElement("活动标签");
        columns.addElement("主办服务队id");
        columns.addElement("名额");
        columns.addElement("联系方式");
        columns.addElement("活动描述");
    }

    private VoingTabModel() {
        super(null, columns);
    }

    private static VoingTabModel voingTabModel = new VoingTabModel();

    public static VoingTabModel assembleModel(Vector<Vector<Object>> data) {
        voingTabModel.setDataVector(data, columns);
        return voingTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {
        voingTabModel.setDataVector(data, columns);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}