package com.fivechan.forum.apps.backend.controllers.topic;

import com.fivechan.forum.context.user.application.UserAuthService;
import com.fivechan.forum.context.user.domain.UserAuthDTO;
import com.fivechan.forum.context.user.domain.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private final UserAuthService userAuthService;

    @Autowired
    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserAuthDTO userAuthDTO) {
        try {
            userAuthService.registerUser(userAuthDTO.getUsername(), userAuthDTO.getPassword(), userAuthDTO.getEmail());
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuth> loginUser(@RequestBody UserAuthDTO userAuthDTO) {
        UserAuth userAuth = userAuthService.authenticate(userAuthDTO.getUsername(), userAuthDTO.getPassword());
        if (userAuth != null) {
            return new ResponseEntity<>(userAuth, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
