package com.github.yjgbg.demo201202.jpa.entity;

import com.github.yjgbg.demo201202.jpa.support.Repos;
import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
public class AccessToken implements ActiveEntity<AccessToken> {
    @Id
    private String token;
    private String appId;
    private String username;
    private Instant createInstant;
    private Instant expireInstant;

    public void expire() {
        remove();
    }

    public UserInfo userInfo() {
        return Repos.repos().userInfoRepo()
                .spec()
                .eq("username",username)
                .findOne()
                .orElseThrow();
    }
}
