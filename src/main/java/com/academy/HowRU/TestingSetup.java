package com.academy.HowRU;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.QuestionSet.dataModels.ResponseType;
import com.academy.HowRU.QuestionSet.services.QuestionSetService;
import com.academy.HowRU.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/***
 * Creates a number of things to test our services and data structures.
 * Populates the database with users, questions and so on so that we can test the API responses.
 */
@Service
public class TestingSetup {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private QuestionSetService  questionSetService;

    private final Logger log = LoggerFactory.getLogger(TestingSetup.class);


    public TestingSetup(UserService us,
                     PasswordEncoder enc, QuestionSetService qss) {
        userService=us;
        passwordEncoder = enc;
        questionSetService = qss;

        initTestStuff();
    }


    private void initTestStuff(){
        createSomeUsers();
        createAQuestionSet();
        createAQuestion();
    }


    private void createSomeUsers(){

        var names = new String[]{"Adam", "Bengt", "John", "Jack"};
        for(var n:names){
            if(userService.registerNewUser(n,n,n+"@gmail.com")){
                log.info("Created user:" + n + ", with password:" + n);
            }
        }
    }

    private void createAQuestionSet(){
        QuestionSet qs = questionSetService.createNewQuestionSet("QSName1", "Adam");
        log.info(qs.getName(), qs.getCreator());
        questionSetService.createNewQuestionSet("QSName2", "Jack");
        questionSetService.createNewQuestionSet("QSName3", "Bengt");
    }

    private void  createAQuestion(){
        questionSetService.createNewQuestion("QSName1","Adam", ResponseType.TEXTFIELD,"Your age?");
        questionSetService.createNewQuestion("QSName1","Adam", ResponseType.SLIDER,"How are you feeling?");

        questionSetService.createNewQuestion("QSName3","Bengt", ResponseType.CHECKBOX,"Check all that apply");
        questionSetService.createNewQuestion("QSName3","Bengt", ResponseType.RADIO,"Pick one");



    }

}
