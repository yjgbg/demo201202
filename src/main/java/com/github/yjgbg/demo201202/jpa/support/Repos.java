package com.github.yjgbg.demo201202.jpa.support;

import com.github.yjgbg.demo201202.jpa.repo.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@Component
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Repos {
    private static Repos repos;

    private final AccessTokenRepo accessTokenRepo;
    private final AppCredentialRepo appCredentialRepo;
    private final AppInfoRepo appInfoRepo;
    private final UserCredentialRepo userCredentialRepo;
    private final UserInfoRepo userInfoRepo;

    public static Repos repos() {
        return repos;
    }

    @Resource
    public void injectSelf(Repos repos) {
        Repos.repos = repos;
    }
}
