package com.cqyc.food.controller;


import com.alibaba.fastjson.JSONObject;
import com.cqyc.food.comm.JwtProperties;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.UserInfo;
import com.cqyc.food.service.IBuyerService;
import com.cqyc.food.utils.CookieUtils;
import com.cqyc.food.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@RestController
@Slf4j
@RequestMapping("/buyer")
@EnableConfigurationProperties(JwtProperties.class)
public class BuyerController {

    @Autowired
    private IBuyerService buyerService;

    @Autowired
    private JwtProperties prop;

    @Value("${yc.jwt.cookieName}")
    private String cookieName;

    /**
     * 检测访问需要登录的页面时，需要跳转到这里
     */
    @RequestMapping("/unauth")
    public Object unauth(){
        System.out.println("用户未登录就访问要登陆的页面了");
        Map<String , Object> map = new HashMap<>();
        map.put("code","1000");
        map.put("msg","未登录");
        return map;
    }

    /**
     *  用户登录
     */
    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@RequestParam("loginName") String loginName,
                                           @RequestParam("userPassword") String userPassword,
                                           HttpServletResponse response, HttpServletRequest request) throws Exception {

//        Buyer buyer = buyerService.userLogin(loginName);
        Map<String, String> map = new HashMap<>();
        String token = buyerService.userToken(loginName,userPassword);
        //TODO 写入cookie
        CookieUtils.setCookie(request,response,cookieName,token);
        map.put("code","10086");
        map.put("status","登录成功");
        map.put("token",token);
        return ResponseEntity.ok(map);
    }

    /**
     * 每次校验用户是否登录
     */
    @PostMapping("/isLogin")
    public ResponseEntity<UserInfo> userIsLogin(@CookieValue("YC_TOKEN") String token,HttpServletResponse response,
                                             HttpServletRequest request ){
        if(StringUtils.isBlank(token)){
            //如果没有token，证明没有登录，返回500
            throw new YcException(ExceptionEnums.USER_NOT_LOGIN);
        }
        //解析token
        try {
            UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            //刷新token，重新生成token
            System.out.println("info = " + info.getAccount()+" : "+info.getUserName());
            String newToken = JwtUtils.generateToken(info,prop.getPrivateKey(),prop.getExpire());
            //写入cookie中
            CookieUtils.setCookie(request,response,cookieName,newToken);
            //返回用户登录信息
            return ResponseEntity.ok(info);
        }catch (Exception e){
            throw new YcException(ExceptionEnums.UN_AUTHORIZED);
        }
    }

    /**
     * 退出登录
     */
    @GetMapping("/loginOut")
    public ResponseEntity<Void> userLoginOut(HttpServletResponse response,
                                             HttpServletRequest request){
        /*Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();*/
        CookieUtils.deleteCookie(request,response,cookieName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     *  用户注册
     */
    @PostMapping("/register")
    public ResponseEntity<Void> userRegister(@RequestBody Buyer buyer){
        buyerService.userRegister(buyer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
