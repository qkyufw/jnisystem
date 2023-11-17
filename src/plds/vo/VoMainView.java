package plds.vo;

import com.dao.impl.VolunteerDaoImpl;
import pojo.Vista;
import pojo.Volunteer;
import pojo.VolunteeringForMine;
import pojo.VolunteeringForVo;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Vector;

public class VoMainView extends JFrame {
    public VoMainView(Volunteer vo) {
        super("jnisystem");
        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());
//        mainView = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        setContentPane(tabbedPane);

        //初始化表单
        JTable myVoingTab = getMyVoingTab(vo.getUid());
        JTable voingOfAllTab = getVoingOfAllTab();  //导入表单
        JTable vistaTab = getVistaTab();  //导入表单

        //我的活动标签
        JPanel allMyVoing = new JPanel();  //我的活动大页面
        JPanel voingOfAll = new JPanel();  //活动大厅大页面
        JPanel vistaOfAll = new JPanel();  //大页面

        //设置标签页布局
        allMyVoing.setLayout(new BorderLayout());  //我的活动大页面布局
        voingOfAll.setLayout(new BorderLayout());  //活动大厅大页面布局
        vistaOfAll.setLayout(new BorderLayout());  //服务队大页面布局

        //初始化并导入表单
        JScrollPane myVoing = new JScrollPane(myVoingTab);  //导入我的表单
        JScrollPane voingOfAllSub = new JScrollPane(voingOfAllTab);  //导入活动大厅表单
        JScrollPane vista = new JScrollPane(vistaTab);  //导入服务队表单

        //初始化菜单
        JPanel myVoingMenu = new JPanel();  //我的活动左边菜单页面
        JPanel voingMenu = new JPanel();  //活动中心左边菜单页面
        JPanel vistaMenu = new JPanel();  //服务队表左边菜单页面

        //设置菜单布局
        myVoingMenu.setLayout(new FlowLayout());  //我的活动的菜单布局
        voingMenu.setLayout(new FlowLayout());  //活动大厅的菜单布局
        vistaMenu.setLayout(new FlowLayout());  //服务队表菜单布局

        //搜索
        JTextField voingText = new JTextField();
        JButton voingS = new JButton("搜索");
        voingText.setPreferredSize(new Dimension(120, 40));
        voingS.setPreferredSize(new Dimension(120, 40));
        voingMenu.add(voingText);
        voingMenu.add(voingS);

        //初始化按钮并给名字
        JButton myInfo = new JButton("修改个人信息");  //修改个人信息按钮
        JButton myVoingF5 = new JButton("刷新");  //刷新我的活动
        JButton voingAllF5 = new JButton("刷新");  //刷新
        JButton vistaF5 = new JButton("刷新");
        JButton vistaMy = new JButton("我的服务队");

        //设置按钮大小
        myInfo.setPreferredSize(new Dimension(120, 40));  //设置按钮大小
        myVoingF5.setPreferredSize(new Dimension(120, 40));
        voingAllF5.setPreferredSize(new Dimension(120, 40));
        vistaF5.setPreferredSize(new Dimension(120, 40));
        vistaMy.setPreferredSize(new DimensionUIResource(120, 40));

        //放置菜单上的按钮
        myVoingMenu.add(myInfo);
        myVoingMenu.add(myVoingF5);
        voingMenu.add(voingAllF5);
        vistaMenu.add(vistaF5);
        vistaMenu.add(vistaMy);
        //放置布局
        allMyVoing.add(myVoingMenu, BorderLayout.WEST);
        allMyVoing.add(myVoing, BorderLayout.CENTER);
        voingOfAll.add(voingMenu, BorderLayout.WEST);  //放置菜单页面
        voingOfAll.add(voingOfAllSub, BorderLayout.CENTER);  //放置中间主页面位置
        vistaOfAll.add(vistaMenu, BorderLayout.WEST);
        vistaOfAll.add(vista, BorderLayout.CENTER);

        //设置菜单布局大小
        myVoingMenu.setPreferredSize(new Dimension(120, 1080));
        voingMenu.setPreferredSize(new Dimension(120, 1080));
        vistaMenu.setPreferredSize(new Dimension(120, 1080));

        tabbedPane.addTab("活动大厅", voingOfAll);
        tabbedPane.addTab("我的活动", allMyVoing);
        tabbedPane.addTab("服务队", vistaOfAll);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        //监听器
        myInfo.addActionListener(e -> new UpdateMyInfo(vo.getUid()));
        myVoingF5.addActionListener(e -> reMyVoingTab(vo.getUid()));
        voingAllF5.addActionListener(e -> reVoingOfAllTab());
        vistaF5.addActionListener(e -> reVistaTab());
        vistaMy.addActionListener(e -> myVistaTab(vo.getUid()));
        voingOfAllTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = voingOfAllTab.getSelectedRow();
                int i = JOptionPane.showConfirmDialog(myVoing, "确认报名" + voingOfAllTab.getValueAt(r, 1) + "活动?", "报名活动", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (i == 0) {
                    VolunteerDaoImpl volunteerDao = new VolunteerDaoImpl();
                    if (volunteerDao.signVoing(vo.getUid(), (Integer) voingOfAllTab.getValueAt(r, 0)) > 0) {
                        JOptionPane.showMessageDialog(myVoing, "报名成功", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(myVoing, "报名失败请重试", "jnisystem", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        vistaTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = vistaTab.getSelectedRow();
                int i = JOptionPane.showConfirmDialog(vista, "确认报名" + vistaTab.getValueAt(r, 1), "jnisystem", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (i == 0) {
                    VolunteerDaoImpl volunteerDao = new VolunteerDaoImpl();
                    if (volunteerDao.addTeam((Integer) vistaTab.getValueAt(r, 0), vo.getUid()) > 0) {
                        JOptionPane.showMessageDialog(vista, "已报名，等待管理员审核", "jnisystem", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(vista, "报名失败请重试", "jnisystem", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // 添加选项卡选中状态改变的监听器
        tabbedPane.addChangeListener(e -> System.out.println("当前选中的选项卡: " + tabbedPane.getSelectedIndex()));

        // 设置默认选中的选项卡
        tabbedPane.setSelectedIndex(0);
    }

    private JTable getVoingOfAllTab() {
        Vector<Vector<Object>> data = new Vector<>();
        VolunteerDaoImpl vo = new VolunteerDaoImpl();
        for (VolunteeringForVo forVo : vo.read()) {
            Vector<Object> hang = new Vector<Object>();
            hang.addElement(forVo.getNumber());
            hang.addElement(forVo.getName());
            hang.addElement(forVo.getDate());
            hang.addElement(forVo.getPlace());
            hang.addElement(forVo.getSumOP());
            hang.addElement(forVo.getDescribe());
            hang.addElement(forVo.getTap());
            hang.addElement(forVo.getVista_id());
            hang.addElement(forVo.getVi_name());
            hang.addElement(forVo.getContact());
            data.add(hang);
        }
        VoingOfAllTabModel voingOfAllTabModel = VoingOfAllTabModel.assembleModel(data);
        JTable jTable = new JTable(voingOfAllTabModel);
        type(jTable);
        return jTable;
    }
    private void reVoingOfAllTab() {  //刷新活动大厅
        Vector<Vector<Object>> data = new Vector<>();
        VolunteerDaoImpl vo = new VolunteerDaoImpl();
        for (VolunteeringForVo forVo : vo.read()) {
            Vector<Object> hang = new Vector<Object>();
            hang.addElement(forVo.getNumber());
            hang.addElement(forVo.getName());
            hang.addElement(forVo.getDate());
            hang.addElement(forVo.getPlace());
            hang.addElement(forVo.getSumOP());
            hang.addElement(forVo.getDescribe());
            hang.addElement(forVo.getTap());
            hang.addElement(forVo.getVista_id());
            hang.addElement(forVo.getVi_name());
            hang.addElement(forVo.getContact());
            data.add(hang);
        }
        VoingOfAllTabModel.updateModel(data);
    }

    private JTable getMyVoingTab(int id) {  //我的活动表
        Vector<Vector<Object>> data = new Vector<>();
        VolunteerDaoImpl vo = new VolunteerDaoImpl();
        for (VolunteeringForMine forVo : vo.readMine(id)) {
            Vector<Object> hang = new Vector<Object>();
            hang.addElement(forVo.getVoing_id());
            hang.addElement(forVo.getVoing_name());
            hang.addElement(forVo.getTap());
            hang.addElement(forVo.getDescribe());
            hang.addElement(forVo.getDate());
            hang.addElement(forVo.getPlace());
            hang.addElement(forVo.getContact());
            hang.addElement(forVo.getVi_name());
            data.add(hang);
        }
        MyVoingTabModel myVoingTabModel = MyVoingTabModel.assembleModel(data);
        JTable jTable = new JTable(myVoingTabModel);
        type(jTable);
        return jTable;
    }

    private void reMyVoingTab(int id) {  //刷新我的活动
        Vector<Vector<Object>> data = new Vector<>();
        VolunteerDaoImpl vo = new VolunteerDaoImpl();
        for (VolunteeringForMine forVo : vo.readMine(id)) {
            Vector<Object> hang = new Vector<Object>();
            hang.addElement(forVo.getVoing_id());
            hang.addElement(forVo.getVoing_name());
            hang.addElement(forVo.getTap());
            hang.addElement(forVo.getDescribe());
            hang.addElement(forVo.getDate());
            hang.addElement(forVo.getPlace());
            hang.addElement(forVo.getContact());
            hang.addElement(forVo.getVi_name());
            data.add(hang);
        }
        MyVoingTabModel.updateModel(data);
    }

    private JTable getVistaTab() {
        Vector<Vector<Object>> data = new Vector<>();
        VolunteerDaoImpl vo = new VolunteerDaoImpl();
        for (Vista forVo : vo.selectAllVista()) {
            Vector<Object> hang = new Vector<Object>();
            hang.addElement(forVo.getNumber());
            hang.addElement(forVo.getName());
            hang.addElement(forVo.getDescribe());
            hang.addElement(forVo.getType());
            hang.addElement(forVo.getName_leader());
            hang.addElement(forVo.getPhone_leader());
            hang.addElement(forVo.getCollege());
            data.add(hang);
        }
        VistaTabModel vistaTabModel = VistaTabModel.assembleModel(data);
        JTable jTable = new JTable(vistaTabModel);
        type(jTable);
        return jTable;
    }

    private void reVistaTab() {
        Vector<Vector<Object>> data = new Vector<>();
        VolunteerDaoImpl vo = new VolunteerDaoImpl();
        for (Vista forVo : vo.selectAllVista()) {
            Vector<Object> hang = new Vector<Object>();
            hang.addElement(forVo.getNumber());
            hang.addElement(forVo.getName());
            hang.addElement(forVo.getDescribe());
            hang.addElement(forVo.getType());
            hang.addElement(forVo.getName_leader());
            hang.addElement(forVo.getPhone_leader());
            hang.addElement(forVo.getCollege());
            data.add(hang);
        }
        VistaTabModel.updateModel(data);
    }

    private void myVistaTab(int id) {
        Vector<Vector<Object>> data = new Vector<>();
        VolunteerDaoImpl vo = new VolunteerDaoImpl();
        for (Vista forVo : vo.selectMy(id)) {
            Vector<Object> hang = new Vector<Object>();
            hang.addElement(forVo.getNumber());
            hang.addElement(forVo.getName());
            hang.addElement(forVo.getDescribe());
            hang.addElement(forVo.getType());
            hang.addElement(forVo.getName_leader());
            hang.addElement(forVo.getPhone_leader());
            hang.addElement(forVo.getCollege());
            data.add(hang);
        }
        VistaTabModel.updateModel(data);
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
        VolunteerDaoImpl volunteerDao = new VolunteerDaoImpl();
        Volunteer vo = volunteerDao.selectOne(1);
        new VoMainView(vo);
    }
}

//我的活动
class MyVoingTabModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("活动编号");
        columns.addElement("活动名称");
        columns.addElement("活动标签");
        columns.addElement("活动描述");
        columns.addElement("活动日期");
        columns.addElement("活动地点");
        columns.addElement("联系方式");
        columns.addElement("主办服务队名称");
    }
    private MyVoingTabModel() {
        super(null, columns);  //初始数据
    }

    private static MyVoingTabModel myVoingTabModel = new MyVoingTabModel();

    public static MyVoingTabModel assembleModel(Vector<Vector<Object>> data) {
        //组装表格
        myVoingTabModel.setDataVector(data, columns);  //更新表格
        return myVoingTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {  //更新表格
        myVoingTabModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

//我的活动
class VoingOfAllTabModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("活动编号");
        columns.addElement("活动名称");
        columns.addElement("活动日期");
        columns.addElement("活动地点");
        columns.addElement("待招募人数");
        columns.addElement("活动描述");
        columns.addElement("活动标签");
        columns.addElement("主办服务队id");
        columns.addElement("主办服务队");
        columns.addElement("联系方式");
    }
    private VoingOfAllTabModel() {
        super(null, columns);  //初始数据
    }

    private static VoingOfAllTabModel voingOfAllTabModel = new VoingOfAllTabModel();

    public static VoingOfAllTabModel assembleModel(Vector<Vector<Object>> data) {
        //组装表格
        voingOfAllTabModel.setDataVector(data, columns);  //更新表格
        return voingOfAllTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {  //更新表格
        voingOfAllTabModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

class VistaTabModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("服务队编号");
        columns.addElement("服务队名称");
        columns.addElement("服务队描述");
        columns.addElement("服务队类型");
        columns.addElement("队长姓名");
        columns.addElement("队长电话");
        columns.addElement("归属学院");
    }
    private VistaTabModel() {
        super(null, columns);  //初始数据
    }

    private static VistaTabModel vistaTabModel = new VistaTabModel();

    public static VistaTabModel assembleModel(Vector<Vector<Object>> data) {
        //组装表格
        vistaTabModel.setDataVector(data, columns);  //更新表格
        return vistaTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {  //更新表格
        vistaTabModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}