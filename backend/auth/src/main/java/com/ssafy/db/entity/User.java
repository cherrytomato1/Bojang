package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.security.OAuthProvider;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class User {
    @Id @GenericGenerator(name="userIdGenerator", strategy = "com.ssafy.db.util.IdGenerator")
    @GeneratedValue(generator = "userIdGenerator")
    private String id;

    private String name;

    private String email;

    private String imageUrl;

    private Boolean emailVerified = false;

    @JsonIgnore
    private String password = null;

    private OAuthProvider provider;

    private String authId;
}
