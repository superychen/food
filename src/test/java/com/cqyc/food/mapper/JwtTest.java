package com.cqyc.food.mapper;

import com.cqyc.food.domain.UserInfo;
import com.cqyc.food.utils.JwtUtils;
import com.cqyc.food.utils.RsaUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    private static final String pubKeyPath = "C:\\Users\\dell\\Desktop\\嘿嘿嘿\\springbootProject\\food\\rsa.pub";
    private static final String priKeyPath = "C:\\Users\\dell\\Desktop\\嘿嘿嘿\\springbootProject\\food\\rsa.pri";

    private PublicKey  publicKey;
    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath,priKeyPath,"woyebuzhidaoxiexiesha");
    }

    @Test
    public void testGenRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        System.out.println("this.publicKey = " + this.publicKey);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        System.out.println("this.privateKey = " + this.privateKey);
    }

    @Test
    public void testGenerateToken() throws Exception {
        //生成token
        String token = JwtUtils.generateToken(new UserInfo("admin","cqyc"),RsaUtils.getPrivateKey(priKeyPath),5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJhY2NvdW50IjoiYWRtaW4iLCJ1c2VyTmFtZSI6ImNxeWMiLCJleHAiOjE1NjA1NzA4NDJ9.jA9Ej3BZLNheDx14MtuMmpMFlbK6J6VAu4RaAsU4rBhQd1yEwXcOUkO1L1j-otc4As2jhbAP09VvsV2ijpxgeAOuxkD4WjiLHeM4LJIP6jBNAhBR3yZY0-BH-UKePpBCSgQPkr1O48hXxGATMCYAl_bYdtXwyJByfURkq1KiZ64\n";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, RsaUtils.getPublicKey(pubKeyPath));
        System.out.println("Account: " + user.getAccount());
        System.out.println("UserName: " + user.getUserName());
    }
}
