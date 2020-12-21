package com.github.yjgbg.demo201202.jpa.entity;

import com.github.yjgbg.demo201202.jpa.behaviors.AppBehavior;
import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class AppCredential implements ActiveEntity<AppCredential>, AppBehavior {
    @Id
    private String appId; //immutable
    private String appSecret;
}
