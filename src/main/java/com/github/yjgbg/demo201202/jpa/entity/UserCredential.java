package com.github.yjgbg.demo201202.jpa.entity;

import com.github.yjgbg.demo201202.jpa.behaviors.UserBehavior;
import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserCredential implements ActiveEntity<UserCredential>, UserBehavior {
    @Id
    private String username; //immutable
    private String password;
}
