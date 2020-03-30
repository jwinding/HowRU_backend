package com.academy.HowRU.QuestionSet.repositories;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
