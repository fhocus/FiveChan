package com.fivechan.forum.context.user.domain;

import jakarta.persistence.*;

@Entity
public class UserAuthEntity {
    @Id
    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private UserEntity userEntity;

    public UserAuthEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserAuthEntity() {
    }

    public UserAuth toDomain() {
        return new UserAuth(username, password, email);
    }

    public static UserAuthEntity fromDomain(UserAuth userAuth) {
        return new UserAuthEntity(userAuth.getUsername(), userAuth.getPassword(), userAuth.getEmail());
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
