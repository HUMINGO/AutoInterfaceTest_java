package com.imooc.model;

public class LoginCase {
    private Integer id;
    private String username;
    private String password;
    private String expect;

    public LoginCase(){

    }

    public LoginCase(Integer id, String username, String password, String expect) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.expect = expect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    @Override
    public String toString() {
        return "LoginCase{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", expect='" + expect + '\'' +
                '}';
    }
}
