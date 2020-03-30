package com.academy.HowRU.QuestionSet.repositories;

import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.user.data.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionSetRepository extends CrudRepository<QuestionSet, Long> {

    List<QuestionSet> findAllByCreator(User user);
}
