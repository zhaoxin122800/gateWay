package com.icbc.gateway.pojo;

public class User {

    private String users;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "users='" + users + '\'' +
                '}';
    }

    public User(String users) {
        this.users = users;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
