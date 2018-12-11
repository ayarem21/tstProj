package com.example.demo.login;

public class LogInRequestBody {
    private String login;
    private String password;
    public LogInRequestBody (String login, String password)
    {
        this.login = login;
        this.password = password;
    }
    public LogInRequestBody() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
