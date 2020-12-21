package com.github.yjgbg.demo201202.jpa.support;

import lombok.val;

import java.util.UUID;

public final class EntityUtils {
    private EntityUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean userExist(String username) {
        val userInfoExist = Repos.repos().userInfoRepo()
                .spec()
                .eq("username",username)
                .exist();
        if (!userInfoExist) return false;
        return Repos.repos().userCredentialRepo()
                .spec()
                .eq("username",username)
                .exist();
    }

    public static boolean appExist(String appId) {
        val userInfoExist = Repos.repos().appInfoRepo()
                .spec()
                .eq("appId",appId)
                .exist();
        if (!userInfoExist) return false;
        return Repos.repos().appCredentialRepo()
                .spec()
                .eq("appId",appId)
                .exist();
    }

    public static String geneUUID() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("-","");
    }
}
