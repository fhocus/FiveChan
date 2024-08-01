package com.fivechan.forum.context.user.infrastructure;

import com.fivechan.forum.context.user.domain.User;
import com.fivechan.forum.context.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PostgresqlUserRepository implements UserRepository {
    @Override
    public void save(User user) {
        // Save user to database
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        return users;
    }

    @Override
    public User findById(UUID id) {
        // Encontrar un usuario por ID en PostgreSQL
        return null;
    }

    @Override
    public User findByUsername(String username) {
        // Encontrar un usuario por nombre de usuario en PostgreSQL
        return null;
    }

    @Override
    public User findByEmail(String email) {
        // Encontrar un usuario por email en PostgreSQL
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        // Eliminar un usuario por ID en PostgreSQL
    }

    @Override
    public void updateById(UUID id, User user) {
        // Actualizar un usuario por ID en PostgreSQL
    }

}
