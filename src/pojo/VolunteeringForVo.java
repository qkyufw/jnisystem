package pojo;

import java.util.Date;

public class VolunteeringForVo {  //用来导出活动中心视图，给志愿者看
    private int number;  //活动编号
    private String name;  //活动名称
    private Date date;  //活动时间
    private String place;  //活动地点
    private int sumOP;  //活动当前人数
    private String describe;  //活动描述
    private String tap;  //活动标签
    private int vista_id;  //主办方id
    private String vi_name;  //主办方名字，不知道为什么名字就写乱了hh
    private String contact;  //主办方联系方式

    public VolunteeringForVo(int number, String name, Date date, String place, int sumOP, String describe, String tap, int vista_id, String vi_name, String contact) {
        this.number = number;
        this.name = name;
        this.date = date;
        this.place = place;
        this.sumOP = sumOP;
        this.describe = describe;
        this.tap = tap;
        this.vista_id = vista_id;
        this.vi_name = vi_name;
        this.contact = contact;
    }

    public VolunteeringForVo() {
    }

    @Override
    public String toString() {
        return "VolunteeringForVo{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", sumOP=" + sumOP +
                ", describe='" + describe + '\'' +
                ", tap='" + tap + '\'' +
                ", vista_id=" + vista_id +
                ", vi_name='" + vi_name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getSumOP() {
        return sumOP;
    }

    public void setSumOP(int sumOP) {
        this.sumOP = sumOP;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTap() {
        return tap;
    }

    public void setTap(String tap) {
        this.tap = tap;
    }

    public int getVista_id() {
        return vista_id;
    }

    public void setVista_id(int vista_id) {
        this.vista_id = vista_id;
    }

    public String getVi_name() {
        return vi_name;
    }

    public void setVi_name(String vi_name) {
        this.vi_name = vi_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
