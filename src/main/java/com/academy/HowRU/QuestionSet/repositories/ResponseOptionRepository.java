package com.academy.HowRU.QuestionSet.repositories;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.options.ResponseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponseOptionRepository extends JpaRepository<ResponseOption, Long> {

    public List<ResponseOption> findByQuestion(Question question);
}
