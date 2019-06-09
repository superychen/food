package com.cqyc.food.controller;


import com.alibaba.fastjson.JSONObject;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.service.IBuyerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class BuyerController {

    @Autowired
    private IBuyerService buyerService;

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
    public ResponseEntity<String> userLogin(@RequestParam("loginName") String loginName,
                                           @RequestParam("userPassword") String userPassword,HttpSession session){

        JSONObject jsonObject = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            //将用户名和密码为usernamePasswordToken
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, userPassword);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                jsonObject.put("token",currentUser.getSession().getId());
                jsonObject.put("msg","登录成功");
            }catch (IncorrectCredentialsException e){
                jsonObject.put("msg","密码错误");
                throw  new AuthenticationException("密码错误");
            } catch (AuthenticationException e){
                jsonObject.put("msg","该用户不存在");
                throw  new AuthenticationException("该用户不存在");
            }
        }
        return ResponseEntity.ok(jsonObject.toString());

        /*Buyer buyer = buyerService.userLogin(loginName, userPassword);
        session.setAttribute("buyer",buyer);*/
    }

    /**
     * 每次校验用户是否登录
     */
    @PostMapping("/isLogin")
    public ResponseEntity<Buyer> userIsLogin(HttpSession session){
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        if(buyer == null){
            throw new YcException(ExceptionEnums.USER_NOT_LOGIN);
        }
        return ResponseEntity.ok(buyer);
    }

    /**
     * 退出登录
     */
    @GetMapping("/loginOut")
    public ResponseEntity<Void> userLoginOut(HttpSession session){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
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
