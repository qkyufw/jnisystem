package test;

import com.dao.impl.VolunteerDaoImpl;
import plds.vo.VoMainView;
import pojo.VolunteeringForVo;

import javax.swing.*;
import java.io.Serializable;
import java.net.URL;
import java.util.Vector;

public class tableTest2 extends JFrame{
    private JTabbedPane tabbedPane;
    private JScrollPane voingOfAll;
    private JPanel me;
    private JPanel vista;
    private JPanel tableTest2;
    private JTable voingOfAllTab;
    private Vector<Vector<Serializable>> rowData;
    private Vector<String> columnNames;

    public tableTest2() {
        super("jnisystem");

        URL imgUrl = VoMainView.class.getClassLoader().getResource("plds/heart.png");
        setIconImage(new ImageIcon(imgUrl).getImage());
//        setContentPane(tableTest2);
//        JTabbedPane jTabbedPane = new JTabbedPane();
//        tabbedPane = new JTabbedPane();

        setContentPane(tabbedPane);
        JButton jButton = new JButton("test");
        columnNames = new Vector<>();  //设置列名
        columnNames.add("number");
        columnNames.add("name");
        columnNames.add("time");
        columnNames.add("place");
        columnNames.add("sumOP");
        columnNames.add("describe");
        columnNames.add("tap");
        columnNames.add("vista_id");
        columnNames.add("vi_name");
        columnNames.add("contact");

        rowData = new Vector<>();
        VolunteerDaoImpl vo = new VolunteerDaoImpl();
        for (VolunteeringForVo forVo : vo.read()) {
            Vector<java.io.Serializable> hang = new Vector<java.io.Serializable>();
            hang.add(forVo.getNumber());
            hang.add(forVo.getName());
            hang.add(forVo.getDate());
            hang.add(forVo.getPlace());
            hang.add(forVo.getSumOP());
            hang.add(forVo.getDescribe());
            hang.add(forVo.getTap());
            hang.add(forVo.getVista_id());
            hang.add(forVo.getVi_name());
            hang.add(forVo.getContact());
            rowData.add(hang);
        }
        voingOfAllTab = new JTable(rowData, columnNames);
        voingOfAll = new JScrollPane(voingOfAllTab);
//        this.add(voingOfAll);
        tabbedPane.addTab("活动大厅", voingOfAll);
        tabbedPane.addTab("我的", me);
        tabbedPane.addTab("服务队", vista);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        LoginHandler loginHandler = new LoginHandler(this);
//        login.addActionListener(loginHandler);
//        register.addActionListener(loginHandler);
        setVisible(true);

        // 添加选项卡选中状态改变的监听器
        tabbedPane.addChangeListener(e -> System.out.println("当前选中的选项卡: " + tabbedPane.getSelectedIndex()));

        // 设置默认选中的选项卡
        tabbedPane.setSelectedIndex(0);

    }

/*    private static JComponent createTextPanel(String text) {
        // 创建面板, 使用一个 1 行 1 列的网格布局（为了让标签的宽高自动撑满面板）
        JPanel panel = new JPanel(new GridLayout(1, 1));

        // 创建标签
        JLabel label = new JLabel(text);
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加标签到面板
        panel.add(label);

        return panel;
    }*/

    public static void main(String[] args) {
        new tableTest2();
    }

}
