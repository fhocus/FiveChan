package com.fivechan.forum.context.user.domain;

public interface UserAuthRepository {
    void save(UserAuth userAuth);
    UserAuth findByUsername(String username);
    UserAuth findByEmail(String email);
    void deleteByUsername(String username);
}
