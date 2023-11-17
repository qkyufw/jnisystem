package pojo;

public class VistaVo {
    private int vi_id;  //服务队编号
    private int vo_id;  //志愿者编号
    private String vo_name;  //志愿者名字
    private String kind;  //志愿者类别
    private int sex;  //志愿者性别
    private int age;  //志愿者年龄
    private String iphone;  //志愿者i志愿绑定号码
    private String phone;  //志愿者电话号码
    private String college;  //志愿者学院
    private int status;  //默认设置为0，未加入

    public VistaVo(int vi_id, int vo_id, String vo_name, String kind, int sex, int age, String iphone, String phone, String college, int status) {
        this.vi_id = vi_id;
        this.vo_id = vo_id;
        this.vo_name = vo_name;
        this.kind = kind;
        this.sex = sex;
        this.age = age;
        this.iphone = iphone;
        this.phone = phone;
        this.college = college;
        this.status = status;
    }

    public VistaVo() {
    }

    public int getVi_id() {
        return vi_id;
    }

    public void setVi_id(int vi_id) {
        this.vi_id = vi_id;
    }

    public int getVo_id() {
        return vo_id;
    }

    public void setVo_id(int vo_id) {
        this.vo_id = vo_id;
    }

    public String getVo_name() {
        return vo_name;
    }

    public void setVo_name(String vo_name) {
        this.vo_name = vo_name;
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

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "VistaVo{" +
                "vi_id=" + vi_id +
                ", vo_id=" + vo_id +
                ", vo_name='" + vo_name + '\'' +
                ", kind='" + kind + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", iphone='" + iphone + '\'' +
                ", phone='" + phone + '\'' +
                ", college='" + college + '\'' +
                ", status=" + status +
                '}';
    }
}
