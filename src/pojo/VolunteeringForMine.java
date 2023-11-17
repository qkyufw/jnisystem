package pojo;

import java.util.Date;

public class VolunteeringForMine {
    private int myid;  //志愿者id
    private int voing_id;  //活动编号
    private String voing_name;  //活动名称
    private String tap;  //活动标签
    private String describe;  //活动描述
    private Date date;  //活动时间
    private String place;  //活动地点
    private String contact;  //活动联系方式
    private String vi_name;  //主办方名字

    public VolunteeringForMine(int myid, int voing_id, String voing_name, String tap, String describe, Date date, String place, String contact, String vi_name) {
        this.myid = myid;
        this.voing_id = voing_id;
        this.voing_name = voing_name;
        this.tap = tap;
        this.describe = describe;
        this.date = date;
        this.place = place;
        this.contact = contact;
        this.vi_name = vi_name;
    }

    public VolunteeringForMine() {
    }

    public int getMyid() {
        return myid;
    }

    public void setMyid(int myid) {
        this.myid = myid;
    }

    public int getVoing_id() {
        return voing_id;
    }

    public void setVoing_id(int voing_id) {
        this.voing_id = voing_id;
    }

    public String getVoing_name() {
        return voing_name;
    }

    public void setVoing_name(String voing_name) {
        this.voing_name = voing_name;
    }

    public String getTap() {
        return tap;
    }

    public void setTap(String tap) {
        this.tap = tap;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVi_name() {
        return vi_name;
    }

    public void setVi_name(String vi_name) {
        this.vi_name = vi_name;
    }

    @Override
    public String toString() {
        return "VolunteeringForMine{" +
                "myid=" + myid +
                ", voing_id=" + voing_id +
                ", voing_name='" + voing_name + '\'' +
                ", tap='" + tap + '\'' +
                ", describe='" + describe + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", contact='" + contact + '\'' +
                ", vi_name='" + vi_name + '\'' +
                '}';
    }
}
