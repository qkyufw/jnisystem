package test;

import com.dao.impl.VolunteerDaoImpl;
import pojo.VolunteeringForVo;

import javax.swing.*;
import java.util.Vector;

public class tableTest extends JFrame {  //活动中心的视图
    private JTable table1;
    private JScrollPane voingofall;

    private Vector<Vector<java.io.Serializable>> rowData;
    private Vector<String> columnNames;

    public tableTest() {
        //初始化
        this.setLocationRelativeTo(null);  //居中
        columnNames = new Vector<String>();  //设置列名
        columnNames.add("number");
        columnNames.add("name");
        columnNames.add("date");
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
        table1 = new JTable(rowData, columnNames);
        voingofall = new JScrollPane(table1);
        this.add(voingofall);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        tableTest tableTest = new tableTest();
        tableTest.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
