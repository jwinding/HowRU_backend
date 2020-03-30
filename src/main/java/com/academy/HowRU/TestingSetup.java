package com.academy.HowRU;

import com.academy.HowRU.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;


/***
 * Creates a number of things to test our services and data structures.
 * Populates the database with users, questions and so on so that we can test the API responses. 
 */
public class TestingSetup {

    private UserService userService;
    private PasswordEncoder passwordEncoder;


    public TestingSetup(UserService us,
                     PasswordEncoder enc) {
        userService=us;
        passwordEncoder = enc;

        initTestStuff();
    }


    private void initTestStuff(){
        createSomeUsers();

    }


    private void createSomeUsers(){

        var names = new String[]{"Adam", "Bengt", "John", "Jack"};
        for(var n:names){
            userService.registerNewUser(n,n,n+"@gmail.com");
        }
    }
}
