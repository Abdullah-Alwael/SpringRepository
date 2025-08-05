package com.spring.boot.springrepository.Service;

import com.spring.boot.springrepository.Api.ApiException;
import com.spring.boot.springrepository.Model.User;
import com.spring.boot.springrepository.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer userId, User user){

        User oldUser = userRepository.findUserById(userId);

        if (oldUser == null){
            throw new ApiException("Error, user not found");
        }

        oldUser.setName(user.getName());
        oldUser.setUserName(user.getUserName());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());

        userRepository.save(oldUser);
    }

    public void deleteUser(Integer userId){

        User oldUser = userRepository.giveMeUserById(userId);

        if (oldUser == null){
            throw new ApiException("Error, user not found");
        }

        userRepository.delete(oldUser);
    }

    public void checkUserNameAndPassword(String userName, String password){
        User checkedUser = userRepository.findUserByUserNameAndPassword(userName,password);

        if (checkedUser == null){
            throw new ApiException("Error, user name and/or password are not correct");
        }

        // nothing else to do
    }

    public User getUserByEmail(String email){
        User userByEmail = userRepository.giveMeUserByEmail(email);

        if (userByEmail == null){
            throw new ApiException("Error, user not found with the specified email");
        }

        return userByEmail;
    }

    public List<User> getAllUsersByRole(String role){
        return userRepository.findUsersByRole(role);
    }

    public List<User> getAllUsersWithAgeOrAbove(Integer age){
        return userRepository.giveMeUsersByAgeOrAbove(age);
    }
}
