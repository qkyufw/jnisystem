package pojo;

import java.util.Date;

public class Volunteering {
    private int number;  //活动编号
    private String name;  //活动名
    private Date data;  //活动日期
    private String place;  //活动地点
    private String tap;  //活动标签
    private int vista_id;  //主办方服务队id
    private int sumOP;  //活动当前招募人数
    private String contact;  //活动方联系方式
    private String describe;  //活动描述

    public Volunteering(int number, String name, Date data, String place, String tap, int vista_id, int sumOP, String contact, String describe) {
        this.number = number;  //活动编号
        this.name = name;  //活动名
        this.data = data;  //活动时间
        this.place = place;  //活动地点
        this.tap = tap;  //活动标签
        this.vista_id = vista_id;  //负责服务队id
        this.sumOP = sumOP;  //活动人数
        this.contact = contact;  //活动方联系方式
        this.describe = describe;  //活动描述
    }

    public Volunteering() {

    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public int getSumOP() {
        return sumOP;
    }

    public void setSumOP(int sumOP) {
        this.sumOP = sumOP;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Volunteering{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", data=" + data +
                ", place='" + place + '\'' +
                ", tap='" + tap + '\'' +
                ", vista_id=" + vista_id +
                ", sumOP=" + sumOP +
                ", contact='" + contact + '\'' +
                '}';
    }
}
