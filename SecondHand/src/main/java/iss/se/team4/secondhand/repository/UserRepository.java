package iss.se.team4.secondhand.repository;

import iss.se.team4.secondhand.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //add


    //delete
    void deleteById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id IN :ids")
    void deleteBatch(@Param("ids") List<Integer> ids);

    //update
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :#{#user.username}, " +
            "u.password = :#{#user.password}, " +
            "u.name = :#{#user.name}, " +
            "u.phone = :#{#user.phone}, " +
            "u.email = :#{#user.email}, " +
            "u.avatar = :#{#user.avatar}, " +
            "u.role = :#{#user.role} " +
            "WHERE u.id = :#{#user.id}")
    void updateById(@Param("user") User user);

    //search
    Optional<User> findById(Integer id);

    User findByUsername(String username);

    List<User> findAll();

    @Query("SELECT u from User u " )
    Page<User> getUserPage(Pageable pageable);
}
