package com.javacourse.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yuliia Tesliuk
 */
@Builder
@Getter
public class User {

    public enum Role {UNKNOWN, ADMIN, USER}
    public enum Department{Accounting, OPR, IT_Support}

    @Setter
    private long id;
    private String firstName;
    private String lastName;
    private Role role;
    private Department department;
    private String position;
    private String email;
    private String login;
    private String password;

    @Override
    public String toString() {
        return "id = " + id + " name " + lastName;
    }
}
