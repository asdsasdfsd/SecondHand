package iss.se.team4.secondhand.repository;

import iss.se.team4.secondhand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, Integer> {
}
