package com.fivechan.forum.context.user.application;

import com.fivechan.forum.context.user.domain.UserAuth;
import com.fivechan.forum.context.user.domain.UserAuthRepository;

import java.util.UUID;

public class CreateUserAuth {
    private final UserAuthRepository userAuthRepository;

    public CreateUserAuth(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public void execute(UUID id, String username, String password, String email) {
        UserAuth userAuth = new UserAuth(id, username, password, email);
        this.userAuthRepository.save(userAuth);
    }
}
