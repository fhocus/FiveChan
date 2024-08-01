package com.fivechan.forum.context.user.infrastructure;

import com.fivechan.forum.context.user.application.CreateUser;
import com.fivechan.forum.context.user.application.FindUserById;
import com.fivechan.forum.context.user.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private CreateUser createUser;
    private FindUserById findUserById;

    @PostMapping
    public void createUser(@RequestBody User user) {
        createUser.execute(user.getId(), user.getName(), user.getDescription(), user.getAvatar());
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
        return findUserById.execute(id);
    }
}
