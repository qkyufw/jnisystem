package pojo;

public class Volunteer {  //志愿者类
    private int uid;  //志愿者学号
    private String name;  //志愿者姓名
    private String pwd;  //志愿者密码
    private String phone;  //志愿者电话
    private String iphone;  //志愿者i志愿绑定手机号
    private String college;  //志愿者学院
    private String kind;  //n内招 w外招
    private int sex;  //1男 2女
    private int age;  //志愿者年龄

    public Volunteer(int uid, String name, String pwd, String phone, String iphone, String college, String kind, int sex, int age) {
        this.uid = uid;
        this.name = name;
        this.pwd = pwd;
        this.phone = phone;
        this.iphone = iphone;
        this.college = college;
        this.kind = kind;
        this.sex = sex;
        this.age = age;
    }

    public Volunteer() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", phone='" + phone + '\'' +
                ", iphone='" + iphone + '\'' +
                ", college='" + college + '\'' +
                ", kind='" + kind + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
