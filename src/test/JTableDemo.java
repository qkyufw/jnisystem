package test;

import com.dao.impl.VistaDaoImpl;
import pojo.VistaVo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class JTableDemo extends JFrame {
    public JTableDemo() {
        super("测试JTable");
        JTable jTable = getTable();

        Container contentPane = getContentPane();
        JPanel jPanel = new JPanel();
        JButton jButton = new JButton("刷新");
        jPanel.add(jButton);
        JScrollPane jScrollPane = new JScrollPane(jTable);  //jtable要放到滚动面板上
        contentPane.setLayout(new BorderLayout());
        contentPane.add(jPanel, BorderLayout.WEST);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
        setSize(1000, 400);
//        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        jButton.addActionListener(e -> reloadTable());
    }

    private JTable getTable() {
        //定义vector
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (VistaVo vo : vistaDao.readVo(1)) {
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

        // tableModel给JTable
        VoTableModel voTableModel = VoTableModel.assembleModel(data);
//        DefaultTableModel tableModel = new DefaultTableModel();  //需要给数据，列名与数据，够着时可以没有，但要填充
//        tableModel.setDataVector(data, columns);  //设置后JTable和table关联，只需要更新model即可把数据变化反映到table中
        // JTable
        JTable jTable = new JTable(voTableModel);
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));  //字体大小
        tableHeader.setForeground(Color.RED);  //字体颜色
        jTable.setFont(new Font(null, Font.PLAIN, 14));
        jTable.setForeground(Color.black);
        jTable.setRowHeight(30);
        // 设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // 设置表格列的渲染方式
        Vector<String> columns = VoTableModel.getColumns();
        VoCellRender render = new VoCellRender();
        for (String s : columns) {
            TableColumn column = jTable.getColumn(s);
            column.setCellRenderer(render);  //每一行的格式
        }
        return jTable;
    }

    public void reloadTable() {  //只适用于这一张表
        Vector<Vector<Object>> data = new Vector<>();  //这些可能改变
        VistaDaoImpl vistaDao = new VistaDaoImpl();
        for (VistaVo vo : vistaDao.readVo(1)) {
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
        VoTableModel.updateModel(data);
    }
    public static void main(String[] args) {
        new JTableDemo();
    }
}

class VoCellRender extends DefaultTableCellRenderer {  //可以公用
    //在每一行每一列显示前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row % 2 == 0) {
            //偶数行
            setBackground(Color.LIGHT_GRAY);
        }else {
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);  //居中
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

// 自定义tableModel
class VoTableModel extends DefaultTableModel {  //只适用于这一张表
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
    private VoTableModel() {
        super(null, columns);  //初始没数据
    }

    private static VoTableModel voTableModel = new VoTableModel();

    public static VoTableModel assembleModel(Vector<Vector<Object>> data) {
        //组装表格
        voTableModel.setDataVector(data, columns);  //更新表格
        return voTableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {
        //组装表格
        voTableModel.setDataVector(data, columns);  //更新表格
    }

    public static Vector<String> getColumns() {  //获取列名
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}