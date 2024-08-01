package com.fivechan.forum.context.user.application;

import com.fivechan.forum.context.user.domain.UserAuth;
import com.fivechan.forum.context.user.domain.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    private final UserAuthRepository userAuthRepository;

    @Autowired
    public UserAuthService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public void registerUser(String username, String password, String email) {
        this.userAuthRepository.save(new UserAuth(username, password, email));
    }

    public UserAuth authenticate(String username, String password) {
        UserAuth userAuth = userAuthRepository.findByUsername(username);
        if (userAuth != null && userAuth.getPassword().equals(password)) {
            return userAuth;
        }
        return null;
    }

    public UserAuth findByEmail(String email) {
        return userAuthRepository.findByEmail(email);
    }
}
