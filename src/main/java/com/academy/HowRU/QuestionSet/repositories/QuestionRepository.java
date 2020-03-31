package com.academy.HowRU.QuestionSet.repositories;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    public Optional<Question> findByQuestion(String name);
}
