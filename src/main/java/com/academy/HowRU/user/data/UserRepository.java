package com.academy.HowRU.user.data;

import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {


    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
