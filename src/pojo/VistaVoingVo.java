package pojo;

public class VistaVoingVo {  //服务队一场活动中报名的志愿者
    private int vi_id;
    private int voing_id;
    private String voing_name;
    private int vo_id;
    private String vo_name;
    private String phone;
    private String iphone;
    private int sex;
    private int age;
    private String kind;

    public VistaVoingVo() {
    }

    public VistaVoingVo(int vi_id, int voing_id, String voing_name, int vo_id, String vo_name, String phone, String iphone, int sex, int age, String kind) {
        this.vi_id = vi_id;
        this.voing_id = voing_id;
        this.voing_name = voing_name;
        this.vo_id = vo_id;
        this.vo_name = vo_name;
        this.phone = phone;
        this.iphone = iphone;
        this.sex = sex;
        this.age = age;
        this.kind = kind;
    }

    public int getVi_id() {
        return vi_id;
    }

    public void setVi_id(int vi_id) {
        this.vi_id = vi_id;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "VistaVoingVo{" +
                "vi_id=" + vi_id +
                ", voing_id=" + voing_id +
                ", voing_name='" + voing_name + '\'' +
                ", vo_id=" + vo_id +
                ", vo_name='" + vo_name + '\'' +
                ", phone='" + phone + '\'' +
                ", iphone='" + iphone + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", kind='" + kind + '\'' +
                '}';
    }
}

