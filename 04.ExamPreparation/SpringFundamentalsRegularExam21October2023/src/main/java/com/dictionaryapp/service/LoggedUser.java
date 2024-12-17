package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private long id;
    private String username;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public boolean isLoggedIn() {
        return this.id != 0;
    }

    public void logout() {
        this.id = 0;
        this.username = null;
    }
}
