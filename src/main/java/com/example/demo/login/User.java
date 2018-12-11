package com.example.demo.login;

public class User {
    private String login;
    private String password;
    private String email;

    public User(String login, String password, String email)
    {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public User(){}

    public String getLogin()
    {
        return login;
    }
    public String getPassword()
    {
        return password;
    }
    public String getEmail()
    {
        return email;
    }
    @Override
    public String toString()
    {
        return "User: \n" + "nick -" + login + ", password-" + password + ", email-" + email;
    }
}
