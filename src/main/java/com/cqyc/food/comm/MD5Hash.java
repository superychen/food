package com.cqyc.food.comm;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * md5加密
 */
public class MD5Hash {
    public static String addSalt(String password,String username){
        String algorithmName = "MD5";
        Object credentials = password;//从前台获取密码
        //盐值
        Object salt = ByteSource.Util.bytes(username);
        int hashIterations = 10;//加密的次数
        Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
        return result.toString();
    }
}
