package com.fivechan.forum.context.user.domain;

public class UserDTO {
    private String name;
    private String description;
    private String avatar;
    private String email;

    public UserDTO(String name, String description, String avatar, String email) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
