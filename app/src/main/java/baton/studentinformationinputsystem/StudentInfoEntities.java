package baton.studentinformationinputsystem;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class StudentInfoEntities implements Serializable {
    /**
     *学生信息实体类
     */
    private String name;//姓名
    private String student_id;//学号
    private String id_number;//身份证号
    private String sex;//性别
    private String native_place;//籍贯
    private Date birthday;//生日
    private String school;//学校
    private String major;//专业
    private String student_class;//班级
    private String phone;//手机号
    private String email;//电子邮箱
    private String weixin_number;//微信号
    private String special_skill;//特长，中间用顿号隔开
    Date getBirthday() {
        return birthday;
    }
    String getBirthdaybyString() {
        return new SimpleDateFormat("yyyy-MM-dd",new Locale("zh")).format(birthday);
    }

    String getEmail() {
        return email;
    }

    String getId_number() {
        return id_number;
    }

    String getMajor() {
        return major;
    }

    String getName() {
        return name;
    }

    String getNative_place() {
        return native_place;
    }

    String getPhone() {
        return phone;
    }

    String getSchool() {
        return school;
    }

    String getSex() {
        return sex;
    }

    String getStudent_id() {
        return student_id;
    }

    String getSpecial_skill() {
        return special_skill;
    }

    String getStudent_class() {
        return student_class;
    }

    String getWeixin_number() {
        return weixin_number;
    }

    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    void setEmail(String email) {
        this.email = email;
    }

    void setId_number(String id_number) {
        this.id_number = id_number;
    }

    void setMajor(String major) {
        this.major = major;
    }

    void setName(String name) {
        this.name = name;
    }

    void setNative_place(String native_place) {
        this.native_place = native_place;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    void setSchool(String school) {
        this.school = school;
    }

    void setSex(String sex) {
        this.sex = sex;
    }

    void setSpecial_skill(String special_skill) {
        this.special_skill = special_skill;
    }

    void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    void setWeixin_number(String weixin_number) {
        this.weixin_number = weixin_number;
    }
}
