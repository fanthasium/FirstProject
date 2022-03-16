package com.example.firstproject;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserData {
  // email, MkDate , user info 넣어줄거..?

    public UserData() {}

        public String getEmail () {
            return email;
        }

        public void setEmail (String email){
            this.email = email;
        }

        public String getMkDate () {
            return MkDate;
        }

        public void setMkDate (String mkDate){
            MkDate = mkDate;
        }

        private String email;
        private String MkDate;


}

