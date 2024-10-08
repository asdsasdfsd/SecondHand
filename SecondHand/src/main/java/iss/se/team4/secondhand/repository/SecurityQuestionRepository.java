package iss.se.team4.secondhand.repository;

import iss.se.team4.secondhand.model.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Integer> {
    
    SecurityQuestion findByUsername(String username);
}
