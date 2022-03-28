
package com.example.firstproject.data;

public class AccountData {

    String name;
    String gender;
    String birth;
    String phoneNum;
    String hobby;

    public AccountData(){}

    public AccountData(String name,String gender,String birth,String phoneNum,String hobby){
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phoneNum = phoneNum;
        this.hobby = hobby;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                " Name='" + name + '\'' +
                ", Gender ='" + gender + '\'' +
                ", Birth ='" + birth + '\'' +
                ", PhoneNum ='" + phoneNum + '\'' +
                " Hobby='" + hobby + '\'' +
                '}';
    }
}




