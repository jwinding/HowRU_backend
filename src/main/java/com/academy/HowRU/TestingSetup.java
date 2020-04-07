package com.academy.HowRU;

import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.QuestionSet.dataModels.options.ResponseType;
import com.academy.HowRU.QuestionSet.services.QuestionSetService;
import com.academy.HowRU.UserResponse.services.UserResponseService;
import com.academy.HowRU.errorHandling.exceptions.EntityNotFoundException;
import com.academy.HowRU.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/***
 * Creates a number of things to test our services and data structures.
 * Populates the database with users, questions and so on so that we can test the API responses.
 */
//@Service
public class TestingSetup {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private QuestionSetService  questionSetService;
    private UserResponseService responseService;

    private final Logger log = LoggerFactory.getLogger(TestingSetup.class);


    public TestingSetup(UserService us, UserResponseService urs,
                     PasswordEncoder enc, QuestionSetService qss) throws EntityNotFoundException {
        userService=us;
        passwordEncoder = enc;
        questionSetService = qss;
        responseService = urs;

        //initTestStuff();
    }


    private void initTestStuff() throws EntityNotFoundException {
        createSomeUsers();
        createAQuestionSet();
        createAQuestion();
        createOption();
        createSomeUserResponses();
    }


    private void createSomeUsers(){

        var names = new String[]{"Adam"};
        for(var n:names){
            if(userService.registerNewUser(n,n,n+"@gmail.com") != null){
                log.info("Created user:" + n + ", with password:" + n);
            }
        }
    }

    private void createAQuestionSet() throws EntityNotFoundException {
        QuestionSet qs = questionSetService.createNewQuestionSet("Test tracker 1", "Adam");
        log.info(qs.getName(), qs.getCreator());
//        questionSetService.createNewQuestionSet("QSName2", "Jack");
//        questionSetService.createNewQuestionSet("QSName3", "Bengt");
    }

    private void  createAQuestion() throws EntityNotFoundException {
        questionSetService.createNewQuestion("Test tracker 1","Adam", ResponseType.TEXT,"General comments");
        questionSetService.createNewQuestion("Test tracker 1","Adam", ResponseType.RANGE,"How are you feeling?");


//        questionSetService.createNewQuestion("QSName3","Bengt", ResponseType.CHECKBOX,"Check all that apply");
//        questionSetService.createNewQuestion("QSName3","Bengt", ResponseType.RADIO,"Pick one");

        questionSetService.createNewQuestion("Test tracker 1","Adam", ResponseType.CHECKBOX,"Meals you ate today");

    }

    private void createOption() throws EntityNotFoundException {
        questionSetService.createNewTextFieldOption(1L,"Write down how you feel");
        questionSetService.createNewSliderOption(2L,0,100,"Bad","Good");

        questionSetService.createNewCheckboxOption(3L,50,"Breakfast");
        questionSetService.createNewCheckboxOption(3L,50,"Lunch");
        questionSetService.createNewCheckboxOption(3L,50,"Dinner");
        questionSetService.createNewCheckboxOption(3L,20,"Afternoon snack");

//        questionSetService.createNewCheckboxOption(3L,1,"Bad");
//        questionSetService.createNewCheckboxOption(3L,0,"Bad");
//        questionSetService.createNewCheckboxOption(3L,50,"Normal");
//        questionSetService.createNewCheckboxOption(3L,100,"Good");

//        questionSetService.createNewRadioOption(4L,0,"Bad");
//        questionSetService.createNewRadioOption(4L,50,"Normal");
//        questionSetService.createNewRadioOption(4L,100,"Good");





    }

    private void createSomeUserResponses(){

        LocalDateTime now = LocalDateTime.now();

        for(long i = 0; i<50; i++){
            var date = now.minus(i, ChronoUnit.DAYS);

            responseService.createUserResponse(1L,"Adam", null,Math.random()>0.5 ? "pretty good":"somewhat bad...",date );


            responseService.createUserResponse(2L,"Adam", (int) Math.round(100*Math.sin(i)),null,date );

            if(Math.random()>0.3)
                responseService.createUserResponse(3L,"Adam", null,null,date );

            if(Math.random()>0.4)
                responseService.createUserResponse(4L,"Adam", null,null,date );
            responseService.createUserResponse(5L,"Adam", null,null,date );


        }

//
//        responseService.createUserResponse(4L,"Bengt",null,null );
//        responseService.createUserResponse(1L,"Adam",null,null );
//        responseService.createUserResponse(2L,"Jack",null,null );
//        responseService.createUserResponse(7L,"Adam",75,null );
//        responseService.createUserResponse(8L,"Bengt",null,"Top of the world!" );
    }

}
