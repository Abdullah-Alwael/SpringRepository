package com.spring.boot.springrepository.Repository;

import com.spring.boot.springrepository.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    @Query("select u from User u where u.id=?1")
    User giveMeUserById(Integer id);

    User findUserByUserNameAndPassword(String userName, String password);

    @Query("select u from User u where u.email=?1")
    User giveMeUserByEmail(String email);

    List<User> findUsersByRole(String role);

    @Query("select u from User u where u.age>=?1")
    List<User> giveMeUsersByAgeOrAbove(Integer age);

}
