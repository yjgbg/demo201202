package com.github.yjgbg.demo201202.service;

import com.github.yjgbg.demo201202.jpa.behaviors.UserBehavior;
import com.github.yjgbg.demo201202.jpa.entity.embedTypes.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    public void das() {
        UserBehavior.authUser("dasd","dasda")
                .updateUserinfo("dasda", Gender.UNKNOWN,"dasdas");
    }
}
