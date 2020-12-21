package com.github.yjgbg.demo201202.jpa.behaviors;

import com.github.yjgbg.demo201202.exceptions.BizException;
import com.github.yjgbg.demo201202.jpa.entity.*;
import com.github.yjgbg.demo201202.jpa.entity.embedTypes.Gender;
import com.github.yjgbg.demo201202.jpa.support.EntityUtils;
import com.github.yjgbg.demo201202.jpa.support.Repos;
import lombok.val;

import java.time.Instant;
import java.util.List;

import static com.github.yjgbg.demo201202.jpa.support.EntityUtils.geneUUID;

public interface UserBehavior {
    String getUsername();

    default AccessToken grant2App(String appId) {
        val appExist = EntityUtils.appExist(appId);
        if (!appExist) throw new BizException(404,"没有找到%s对应的app",appId);
        return new AccessToken()
                .setUsername(getUsername())
                .setCreateInstant(Instant.now())
                .setAppId(appId)
                .setToken(EntityUtils.geneUUID())
                .save();
    }

    default List<AppInfo> listGrantedApp() {
        val grantedAppIdList = Repos.repos().accessTokenRepo()
                .spec()
                .eq("username",getUsername())
                .findAll()
                .stream()
                .map(AccessToken::getAppId);
        return Repos.repos().appInfoRepo().spec()
                .in("appId",grantedAppIdList)
                .findAll();
    }

    default void undoGrant2App(String appId) {
        Repos.repos().accessTokenRepo()
                .spec()
                .eq("appId",appId)
                .eq("username",getUsername())
                .findOne()
                .orElseThrow(new BizException(400,"当前用户没有授权给该App，无法撤销授权"))
                .expire();
    }

    default UserInfo updateUserinfo(String description, Gender gender, String nickName) {
        return Repos.repos().userInfoRepo().spec()
                .eq("username",getUsername())
                .findOne()
                .orElseThrow(new BizException(404,"没有找到用户名为%s的用户信息",getUsername()))
                .setDescription(description)
                .setGender(gender)
                .setNickName(nickName)
                .save();
    }

    default void updatePassword(String password) {
        Repos.repos().userCredentialRepo().spec()
                .eq("username",getUsername())
                .findOne()
                .orElseThrow(new BizException(404,"没有找到用户名为%s的用户",getUsername()))
                .setPassword(password)
                .save();
    }

    default AppCredential createApp(String appName, String description) {
        val appId = geneUUID();
        val appSecret = geneUUID();
        new AppInfo().setAppId(appId)
                .setAppName(appName)
                .setDescription(description)
                .setCreateBy(getUsername())
                .save();
        return new AppCredential().setAppId(appId)
                .setAppSecret(appSecret)
                .save();
    }

    static UserInfo registerUser(String username, String password) {
        val usernameConflict = doesUsernameConflict(username);
        if (usernameConflict) throw new BizException(409,"用户名(%s)重复",username);
        new UserCredential()
                .setUsername(username)
                .setPassword(password)
                .save();
        return new UserInfo().setUsername(username)
                .setNickName(username)
                .setGender(Gender.UNKNOWN)
                .setDescription("这个人很懒，什么都没有流下")
                .save();
    }

    static boolean doesUsernameConflict(String username) {
        return Repos.repos().userCredentialRepo().spec()
                .eq("username",username)
                .exist();
    }

    static UserInfo authUser(String username, String password) {
        val validPassword = Repos.repos().userCredentialRepo().spec()
                .eq("username",username)
                .eq("password",password)
                .exist();
        if (!validPassword) throw new BizException(401,"用户不存在或密码错误");
        return Repos.repos().userInfoRepo().spec()
                .eq("username",username)
                .findOne()
                .orElseThrow(new BizException(401,"用户不存在"));
    }
}
