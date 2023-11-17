package plds.vista;

import com.dao.impl.VistaDaoImpl;
import plds.vo.VoMainView;
import pojo.VistaVoingVo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class VoingVo extends JFrame {
    public VoingVo(int voing_id) {
        super("jnisystem");
        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        if (imgUrl != null) setIconImage(new ImageIcon(imgUrl).getImage());  //设置图标
        JPanel jPanel = new JPanel();
        setContentPane(jPanel);

        JTable voTab = getVoingVo(voing_id);
        JPanel menu = new JPanel();  //菜单
        JButton jButton = new JButton("刷新");
        menu.add(jButton);
        JScrollPane tab = new JScrollPane(voTab);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(tab, BorderLayout.CENTER);
        jPanel.add(menu,BorderLayout.WEST);

        menu.setPreferredSize(new Dimension(120, 1080));
        jButton.setPreferredSize(new Dimension(120, 40));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //关闭模式
        setLocationRelativeTo(null);  //位置
        setExtendedState(MAXIMIZED_BOTH);  //大小
        setVisible(true);  //可见
    }

    private JTable getVoingVo(int voing_id) {
        //建表
        //定义vector
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (VistaVoingVo vo : vistaDao.readVoingVo(voing_id)) {
            Vector<Object> hang = new Vector<>();
            hang.addElement(vo.getVo_id());
            hang.addElement(vo.getVo_name());
            hang.addElement(vo.getPhone());
            hang.addElement(vo.getIphone());
            hang.addElement(vo.getSex());
            hang.addElement(vo.getAge());
            hang.addElement(vo.getKind());
            data.addElement(hang);
        }
        //model给table
        VoingVoTabModel voingVoTabModel = VoingVoTabModel.assembleModel(data);

        JTable jTable = new JTable(voingVoTabModel);
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

    public static void main(String[] args) {
        new VoingVo( 1);
    }
}

//队员表模式
class VoingVoTabModel extends DefaultTableModel {  //队员表模式
    static Vector<String> columns = new Vector<>();  //这些是属性
    static {
        columns.addElement("志愿者学号");
        columns.addElement("志愿者姓名");
        columns.addElement("志愿者电话");
        columns.addElement("i志愿绑定号码");
        columns.addElement("志愿者性别");
        columns.addElement("志愿者年龄");
        columns.addElement("志愿者类别");
    }

    private VoingVoTabModel() {
        super(null, columns);  //初始数据
    }

    private static VoingVoTabModel voingVoTabModel = new VoingVoTabModel();

    public static VoingVoTabModel assembleModel(Vector<Vector<Object>> data) {
        //组装表格
        voingVoTabModel.setDataVector(data, columns);  //更新表格
        return voingVoTabModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {  //更新表格
        voingVoTabModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}