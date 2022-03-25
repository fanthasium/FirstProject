package com.example.firstproject.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserData {

    public String mkDate;
    public String email;

    public UserData () {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)  데이터 옮겨짐 !! 중요 !!
    }

    public UserData(String email, String mkDate) {
        this.mkDate = mkDate;
        this.email = email;
    }


    public String getMkDate() {
        return mkDate;
    }

    public void setMkDate(String MkDate) {
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



