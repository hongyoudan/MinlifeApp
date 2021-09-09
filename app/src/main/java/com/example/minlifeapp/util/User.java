package com.example.minlifeapp.util;

public class User {
    private String college;//学院
    private String number;//学号
    private String name;//姓名
    private String gender;//性别
    private String phone;//手机号
    private String dormitory;//宿舍号
    private String password;//密码

    public User(String college, String number, String name, String gender, String phone, String dormitory, String password) {
        this.college = college;
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.dormitory = dormitory;
        this.password = password;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "college='" + college + '\'' +
                "number='" + number + '\'' +
                "name='" + name + '\'' +
                "gender='" + gender + '\'' +
                "phone='" + phone + '\'' +
                "dormitory='" + dormitory + '\'' +
                "password='" + password + '\'' +
                '}';
    }
}

