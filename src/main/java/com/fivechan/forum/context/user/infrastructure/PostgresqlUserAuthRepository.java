package com.fivechan.forum.context.user.infrastructure;

import com.fivechan.forum.context.user.domain.UserAuth;
import com.fivechan.forum.context.user.domain.UserAuthRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresqlUserAuthRepository implements UserAuthRepository {

    @Override
    public UserAuth findByUsername(String username) {
        // Lógica para encontrar una autenticación de usuario por nombre de usuario en PostgreSQL
        return null;
    }

    @Override
    public UserAuth findByEmail(String email) {
        // Lógica para encontrar una autenticación de usuario por email en PostgreSQL
        return null;
    }

    @Override
    public void save(UserAuth userAuth) {
        // Lógica para guardar la autenticación de usuario en PostgreSQL
    }
}
