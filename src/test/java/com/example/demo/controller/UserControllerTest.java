package com.example.demo.controller;


import com.example.demo.login.LogInRequestBody;
import com.example.demo.login.SingUpRequestBody;
import com.example.demo.login.User;
import com.example.demo.util.UserUtil;
import com.example.demo.util.UserUtilImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@Profile("user-util-mock")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    private String correctEmail = "qwerty@gmail.com";
    private String correctLogin = "HarryPotter2001";
    private String correctPassword = "qwerty@1";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private UserUtilImpl userUtil = Mockito.mock(UserUtilImpl.class);

    @Before
    public void beforeSetup(){
        Mockito.when(userUtil.createUser(correctLogin,correctEmail,correctPassword))
                .thenReturn(1L);
        Mockito.when(userUtil.getUserByLogin(correctEmail))
                .thenReturn(new User(correctLogin, correctEmail, correctPassword));
    }
    @Test
    public void signUpTest() {
        ResponseEntity<Long> response = restTemplate.exchange(
                "/api/sign-up",
                HttpMethod.POST,
                new HttpEntity<>(
                        new SingUpRequestBody(correctLogin, correctPassword, correctPassword, correctEmail, correctPassword)),
                Long.class);

        assertEquals(response.getBody(), (Long) 1L);
    }

    @Test
    public void signUpDifferentPassTest(){
        String wrongPassword = "notpass#31";

        ResponseEntity<Long> response = restTemplate.exchange(
                "/api/sign-up",
                HttpMethod.POST,
                new HttpEntity<>(
                        new SingUpRequestBody(correctLogin, correctPassword, correctPassword, wrongPassword, correctPassword)),
                Long.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void logInCorrectParamsTest(){
        ResponseEntity<User> response = restTemplate.exchange(
                "/api/login",
                HttpMethod.POST,
                new HttpEntity<>(
                        new LogInRequestBody(correctEmail, correctPassword)),
                User.class);

        assertEquals(response.getBody().getEmail(),correctEmail);
        assertEquals(response.getBody().getPassword(),correctPassword);
    }

    @Test
    public void logInWrongPasswordTest(){
        String wrongPassword = "blabla#12";

        ResponseEntity<User> response = restTemplate.exchange(
                "/api/login",
                HttpMethod.POST,
                new HttpEntity<>(
                        new LogInRequestBody(correctEmail, wrongPassword)),
                User.class);

        assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void logInWrongEmailTest(){
        String wrongEmail = "Blabla@bla.bla";

        ResponseEntity<User> response = restTemplate.exchange(
                "/api/login",
                HttpMethod.POST,
                new HttpEntity<>(
                        new LogInRequestBody(wrongEmail, correctPassword)),
                User.class);

        assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


