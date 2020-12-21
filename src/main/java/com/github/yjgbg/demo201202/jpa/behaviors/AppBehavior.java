package com.github.yjgbg.demo201202.jpa.behaviors;

import com.github.yjgbg.demo201202.exceptions.BizException;
import com.github.yjgbg.demo201202.jpa.entity.AppInfo;
import com.github.yjgbg.demo201202.jpa.entity.UserInfo;
import com.github.yjgbg.demo201202.jpa.support.Repos;
import lombok.val;

public interface AppBehavior {
    String getAppId();

    static AppBehavior auth(String appId, String appSecret) {
        return Repos.repos().appCredentialRepo().spec()
                .eq("appId",appId)
                .eq("appSecret",appSecret)
                .findOne().orElseThrow(new BizException(401,"AppId不存在或AppSecret错误"));
    }

    default UserInfo accessUserInfo(String token) {
        val username = Repos.repos().accessTokenRepo().spec()
                .eq("token",token)
                .eq("appId",getAppId())
                .findOne()
                .orElseThrow(new BizException(403,"没有权限访问"))
                .getUsername();
        return Repos.repos().userInfoRepo().spec()
                .eq("username",username)
                .findOne()
                .orElseThrow(new BizException(404,"没有找到该用户的信息"));
    }

    default AppInfo updateAppInfo(String appName,String description) {
        return Repos.repos().appInfoRepo().spec()
                .eq("appId",getAppId())
                .findOne()
                .orElseThrow(new BizException(403,"没有权限访问"))
                .setAppName(appName)
                .setDescription(description)
                .save();
    }
}
