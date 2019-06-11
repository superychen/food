package com.cqyc.food.comm;

import com.cqyc.food.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Data
@ConfigurationProperties(prefix = "yc.jwt")
public class JwtProperties {
    private String secret;
    private String pubKeyPath;
    private String priKeyPath;
    private int expire;
    private PublicKey publicKey ;
    private PrivateKey privateKey;
    private String cookieName;

    //对象一旦实例化加载，就应该 读取公钥和私钥
    @PostConstruct//对象被实例化执行后执行
    public void init() throws Exception {
        File file = new File(pubKeyPath);
        File file1 = new File(priKeyPath);
        if(!file.exists() || !file1.exists()){
            //生成公钥和私钥
            RsaUtils.generateKey(pubKeyPath,priKeyPath,secret);
        }
        //读取公钥
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath );
    }
}
