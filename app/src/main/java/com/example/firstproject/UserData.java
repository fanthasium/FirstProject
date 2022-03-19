package com.example.firstproject;

import java.util.Date;

public class UserData {

    public Date mkDate;
    public String email;

    public UserData () {}  //데이터 옮겨짐 !! 중요 !!

    public UserData(String email, Date mkDate) {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        this.mkDate = mkDate;
        this.email = email;
    }


    public Date getMkDate() {
        return mkDate;
    }

    public void setMkDate(Date MkDate) {
        this.mkDate = MkDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "MkDate='" + mkDate + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}



