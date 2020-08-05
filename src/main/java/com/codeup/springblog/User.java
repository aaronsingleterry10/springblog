package com.codeup.springblog;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    private long id;
    private String username;
    private String email;
    private String password;

}
