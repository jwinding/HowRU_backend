package com.academy.HowRU.QuestionSet.repositories;

import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.user.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionSetRepository extends JpaRepository<QuestionSet, Long> {

    List<QuestionSet> findAllByCreator(User user);

    Optional<QuestionSet> findByName(String questionSetName);
}
