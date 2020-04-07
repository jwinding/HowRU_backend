package com.academy.HowRU.UserResponse.repositories;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.options.ResponseOption;
import com.academy.HowRU.UserResponse.dataModels.UserResponse;
import com.academy.HowRU.user.data.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserResponseRepository extends CrudRepository<UserResponse, Long> {

    List<UserResponse> findByUser(User user);

    List<UserResponse> findByOption(ResponseOption option);




}
