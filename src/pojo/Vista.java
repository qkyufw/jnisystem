package pojo;

public class Vista {  //服务队类
    private int number;  //服务队编号
    private String name;  //服务队名字
    private String type;  //服务队类型
    private String describe;  //服务队简介or描述
    private int sumOP;  //服务队人数
    private String name_leader;  //服务队队长名字
    private String phone_leader;  //服务队队长电话
    private int ID_leader;  //服务队队长学号
    private String college;  //服务队归属学院
    private String pwd;  //服务队密码
    private int status;  //0申请中，非正式服务队，需管理员更改

    public Vista(int number, String name, String type, String describe, int sumOP, String name_leader, String phone_leader, int ID_leader, String college, String pwd, int status) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.describe = describe;
        this.sumOP = sumOP;
        this.name_leader = name_leader;
        this.phone_leader = phone_leader;
        this.ID_leader = ID_leader;
        this.college = college;
        this.pwd = pwd;
        this.status = status;
    }

    public Vista() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getSumOP() {
        return sumOP;
    }

    public void setSumOP(int sumOP) {
        this.sumOP = sumOP;
    }

    public String getName_leader() {
        return name_leader;
    }

    public void setName_leader(String name_leader) {
        this.name_leader = name_leader;
    }

    public String getPhone_leader() {
        return phone_leader;
    }

    public void setPhone_leader(String phone_leader) {
        this.phone_leader = phone_leader;
    }

    public int getID_leader() {
        return ID_leader;
    }

    public void setID_leader(int ID_leader) {
        this.ID_leader = ID_leader;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "vista{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", describe='" + describe + '\'' +
                ", sumOP=" + sumOP +
                ", name_leader='" + name_leader + '\'' +
                ", phone_leader='" + phone_leader + '\'' +
                ", ID_leader=" + ID_leader +
                ", college='" + college + '\'' +
                ", pwd='" + pwd + '\'' +
                ", status=" + status +
                '}';
    }
}
