package com.spring.boot.springrepository.Controller;

import com.spring.boot.springrepository.Api.ApiResponse;
import com.spring.boot.springrepository.Model.User;
import com.spring.boot.springrepository.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User added successfully"));

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User updated successfully"));

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User deleted successfully"));
    }

    @GetMapping("/check-username-and-password/{userName}/{password}")
    public ResponseEntity<?> CheckUserNameAndPassword(@PathVariable String userName, @PathVariable String password){

        userService.checkUserNameAndPassword(userName,password); // throws exception if user not found
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Username and password are correct"));

    }

    @GetMapping("/filter/by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));

    }

    @GetMapping("/filter/by-role/{role}")
    public ResponseEntity<?> getAllUsersByRole(@PathVariable String role){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersByRole(role));

    }

    @GetMapping("/filter/by-age/{age}")
    public ResponseEntity<?> getAllUsersByAgeOrAbove(@PathVariable Integer age){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersWithAgeOrAbove(age));

    }
}
