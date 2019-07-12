package com.kerwin.server.pojo;

import jdbc2.Column;
import jdbc2.Table;

@Table(tableName = "User")
public class User {

        @Column("userId")
        private int userId;

        @Column("userName")
        private String userName;

        @Column("userPassword")
        private String userPassword;

        @Column("userEmail")
        private String userEmail;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        @Override
        public String toString() {
            return "user{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", userPassword='" + userPassword + '\'' +
                    ", userEmail='" + userEmail + '\'' +
                    '}';
        }
}
