package com.example.demo.controller;

import com.example.demo.login.LogInRequestBody;
import com.example.demo.login.SingUpRequestBody;
import com.example.demo.login.User;
import com.example.demo.util.UserUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserUtilImpl userUtil;

    @PostMapping("/api/sign-up")
    public @ResponseBody
    Long signUp(@RequestBody SingUpRequestBody reqBody) {
        if (reqBody.getPassword().equals(reqBody.getConfirmationPassword())){
            return this.userUtil.createUser(reqBody.getLogin(), reqBody.getLogin(), reqBody.getPassword());
        }else {
            throw new Error("Passwords does not match");
        }
    }

    @PostMapping("/api/login")
    public @ResponseBody
    User logIn(@RequestBody LogInRequestBody reqBody) {
        return this.userUtil.logInUser(reqBody.getLogin(), reqBody.getPassword());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(Error.class)
    public @ResponseBody String exceptionHandler() {
        return "Wrong request params";
    }
}
