package com.github.yjgbg.demo201202.ctl;

import com.github.yjgbg.demo201202.jpa.entity.AccessToken;
import com.github.yjgbg.jpa.plus.aop.ReturnPropsSetNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserCredentialCtl {
    /**
     *
     * @return
     */
    @GetMapping("/hello")
    @ReturnPropsSetNull("das")
    public static AccessToken vo(Long id) {
        return null;
    }
}
