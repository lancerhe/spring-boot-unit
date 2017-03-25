package com.crackedzone.www.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Package com.crackedzone.www.core.entity
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@Entity
@Table(name = "managers")
public class ManagerEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

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
}