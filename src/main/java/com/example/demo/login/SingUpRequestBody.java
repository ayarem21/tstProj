package com.example.demo.login;

public class SingUpRequestBody {
    private String login;
    private String password;
    private String repassword;
    private String email;
    private String confirmationPassword;

    public SingUpRequestBody(String login,String password,String repassword,String email, String confirmationPassword)
    {
        this.login = login;
        this.password = password;
        this.repassword = repassword;
        this.email = email;
        this.confirmationPassword = confirmationPassword;
    }

    public SingUpRequestBody(){}
    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }
    public String getRepassword()
    {
        return repassword;
    }
    public String getEmail()
    {
        return email;
    }
    public String getConfirmationPassword() {
        return confirmationPassword;
    }
}
