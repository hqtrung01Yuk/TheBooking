package com.group.prj.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.prj.exception.UserAlreadyExistsException;
import com.group.prj.model.User;
import com.group.prj.service.IUserService;

import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
        try {
            User theUser = userService.getUser(email);

            return ResponseEntity.ok(theUser);
        } catch (UserAlreadyExistsException userException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userException.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user");
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String email) {
        try {
            userService.deleteUser(email);

            return ResponseEntity.ok("User deleted successfully!!");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user!!");
        }
    }

}
