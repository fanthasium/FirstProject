
package com.example.firstproject.data;

import android.os.Parcel;
import android.os.Parcelable;

public class AccountData implements Parcelable {

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

    protected AccountData(Parcel in) {
        name = in.readString();
        gender = in.readString();
        birth = in.readString();
        phoneNum = in.readString();
        hobby = in.readString();
    }

    public static final Creator<AccountData> CREATOR = new Creator<AccountData>() {
        @Override
        public AccountData createFromParcel(Parcel in) {
            return new AccountData(in);
        }

        @Override
        public AccountData[] newArray(int size) {
            return new AccountData[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(gender);
        parcel.writeString(birth);
        parcel.writeString(phoneNum);
        parcel.writeString(hobby);
    }
}




