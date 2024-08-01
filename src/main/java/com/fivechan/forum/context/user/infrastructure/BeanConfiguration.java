package com.fivechan.forum.context.user.infrastructure;

import com.fivechan.forum.context.user.application.CreateUser;
import com.fivechan.forum.context.user.application.FindUserById;
import com.fivechan.forum.context.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateUser createUser(UserRepository userRepository) {
        return new CreateUser(userRepository);
    }

    @Bean
    public FindUserById findUserById(UserRepository userRepository) {
        return new FindUserById(userRepository);
    }
}
