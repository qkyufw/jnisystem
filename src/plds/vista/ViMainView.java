package plds.vista;

import com.dao.impl.VistaDaoImpl;
import plds.vo.VoMainView;
import pojo.Vista;
import pojo.VistaVo;
import pojo.Volunteering;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Vector;

public class ViMainView extends JFrame {
    //主页面
    public ViMainView(Vista vista) {  //需要一个服务队类型进入界面
        super("jnisystem");
        URL imgUrl = ViMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标
        JTabbedPane tabbedPane = new JTabbedPane();  //初始化主页面
        setContentPane(tabbedPane);  //选择主页面

        JPanel voingJPanel = new JPanel();  //初始化活动界面
        JPanel teamJPanel = new JPanel();  //初始化队员界面

        JPanel voingMenu = new JPanel();  //初始化活动菜单界面
        JPanel teamMenu = new JPanel();  //初始化队员菜单界面

        JTable tMember = getTeamMember(vista.getNumber());
        JTable voing = getVistaVoing(vista.getNumber());

        JScrollPane voingJSP = new JScrollPane(voing);  //初始化活动表界面
        JScrollPane teamJSP = new JScrollPane(tMember);  //初始化队员数据表界面

        //活动界面
        voingJPanel.setLayout(new BorderLayout());  //活动界面布局
        voingMenu.setLayout(new FlowLayout());  //菜单界面布局
        JButton voingBtn2 = new JButton("添加活动");
        JButton voingF5 = new JButton("刷新");
        voingBtn2.setPreferredSize(new Dimension(120, 40));
        voingF5.setPreferredSize(new Dimension(120, 40));
        voingMenu.add(voingBtn2);
        voingMenu.add(voingF5);
        voingJPanel.add(voingMenu, BorderLayout.WEST);
        voingJPanel.add(voingJSP, BorderLayout.CENTER);
        voingMenu.setPreferredSize(new Dimension(120,1080));

        //队员界面
        teamJPanel.setLayout(new BorderLayout());  //队员界面布局
        teamMenu.setLayout(new FlowLayout());  //菜单界面布局
        JButton teamBtn1 = new JButton("新申请");
        JButton teamBtn2 = new JButton("修改服务队");
        JButton teamF5 = new JButton("刷新");
        teamF5.setPreferredSize(new Dimension(120, 40));
        teamBtn2.setPreferredSize(new Dimension(120, 40));
        teamBtn1.setPreferredSize(new Dimension(120, 40));
        voingMenu.add(teamBtn2);
        teamMenu.add(teamBtn1);  //给菜单加上按钮
        teamMenu.add(teamF5);
        teamJPanel.add(teamMenu, BorderLayout.WEST);
        teamJPanel.add(teamJSP, BorderLayout.CENTER);
        teamMenu.setPreferredSize(new Dimension(120,1080));

        tabbedPane.addTab("我的活动", voingJPanel);
        tabbedPane.addTab("我的队员", teamJPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);  //关闭模式
        setLocationRelativeTo(null);  //位置
        setExtendedState(MAXIMIZED_BOTH);  //大小
        setVisible(true);  //可见

        //监听器
        voingBtn2.addActionListener(e -> new VoingCreate(vista.getNumber()));
        voingF5.addActionListener(e -> reloadVistaVoing(vista.getNumber()));
        voing.addMouseListener(new MouseAdapter() {  //修改功能+未完成的删除功能
            @Override
            public void mouseClicked(MouseEvent e) {
                new UpdateVoing((Integer) voing.getValueAt(voing.getSelectedRow(), 0));
            }
        });

        teamBtn1.addActionListener(e -> new SignedVistaTab(vista.getNumber()));
        teamBtn2.addActionListener(e -> new UpdateVista(vista.getNumber()));
        teamF5.addActionListener(e -> reloadTeamMember(vista.getNumber()));
        tMember.addMouseListener(new MouseAdapter() {  //删除功能
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = tMember.getSelectedRow();
                int count = JOptionPane.showConfirmDialog(teamJPanel, "确认删除" + r, "jnisystem", JOptionPane.YES_NO_CANCEL_OPTION);
                System.out.println(r);
                if (count == 0) {
                    VistaDaoImpl vistaDao = new VistaDaoImpl();
                    vistaDao.deleteTeam(vista.getNumber(), (int) tMember.getValueAt(r, 0));
                }
            }
        });
    }

    //服务队的活动表
    private JTable getVistaVoing(int vi_id) {
        //定义vector
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (Volunteering voing : vistaDao.readVoing(vi_id)) {
            Vector<Object> hang = new Vector<>();
            hang.addElement(voing.getNumber());
            hang.addElement(voing.getName());
            hang.addElement(voing.getDate());
            hang.addElement(voing.getPlace());
            hang.addElement(voing.getDescribe());
            hang.addElement(voing.getTap());
            hang.addElement(voing.getSumOP());
            hang.addElement(voing.getContact());
            data.addElement(hang);
        }
        VoingTabModel voingTabModel = VoingTabModel.assembleModel(data);
        JTable jTable = new JTable(voingTabModel);
        type(jTable);
        return jTable;
    }

    //刷新服务队的活动表
    private void reloadVistaVoing(int vi_id) {
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (Volunteering voing : vistaDao.readVoing(vi_id)) {
            Vector<Object> hang = new Vector<>();
            hang.addElement(voing.getNumber());
            hang.addElement(voing.getName());
            hang.addElement(voing.getDate());
            hang.addElement(voing.getPlace());
            hang.addElement(voing.getDescribe());
            hang.addElement(voing.getTap());
            hang.addElement(voing.getSumOP());
            hang.addElement(voing.getContact());
            data.addElement(hang);
        }
        VoingTabModel.updateModel(data);
    }

    //队员表
    private JTable getTeamMember(int vi_id) {  //建立队员表
        //定义vector
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (VistaVo vo : vistaDao.readVo(vi_id)) {
            if (vo.getStatus() != 0) {  //status不为0，则这些是成员
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
        type(jTable);
        return jTable;
    }

    private void type(JTable jTable) {
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));  //字体大小
        tableHeader.setForeground(Color.RED);  //字体颜色
        jTable.setFont(new Font(null, Font.PLAIN, 14));
        jTable.setForeground(Color.black);
        jTable.setRowHeight(30);
        // 设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    //刷新队员表
    private void reloadTeamMember(int vi_id) {  //刷新队员表
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (VistaVo vo : vistaDao.readVo(vi_id)) {
            if (vo.getStatus() != 0) {
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

    //主函数
    public static void main(String[] args) {
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        Vista vista = vistaDao.login(1, "123456");
        new ViMainView(vista);
    }
}

//活动表模式
class VoingTabModel extends DefaultTableModel{
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("活动编号");
        columns.addElement("活动名称");
        columns.addElement("活动日期");
        columns.addElement("活动地点");
        columns.addElement("活动描述");
        columns.addElement("活动标签");
        columns.addElement("待招募人数");
        columns.addElement("联系方式");
    }
    private VoingTabModel() {
        super(null, columns);  //初始数据
    }

    private static VoingTabModel voingTabModel = new VoingTabModel();

    public static VoingTabModel assembleModel(Vector<Vector<Object>> data) {
        //组装表格
        voingTabModel.setDataVector(data, columns);  //更新表格
        return voingTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {  //更新表格
        voingTabModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

//队员表模式
class TeamMemberTabModel extends DefaultTableModel {  //队员表模式
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("志愿者学号");
        columns.addElement("志愿者姓名");
        columns.addElement("志愿者类别");
        columns.addElement("志愿者性别");
        columns.addElement("志愿者年龄");
        columns.addElement("志愿者电话");
        columns.addElement("i志愿绑定号码");
        columns.addElement("学院");
    }

    private TeamMemberTabModel() {
        super(null, columns);  //初始数据
    }

    private static TeamMemberTabModel teamMemberTabModel = new TeamMemberTabModel();

    public static TeamMemberTabModel assembleModel(Vector<Vector<Object>> data) {
        //组装表格
        teamMemberTabModel.setDataVector(data, columns);  //更新表格
        return teamMemberTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {  //更新表格
        teamMemberTabModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}