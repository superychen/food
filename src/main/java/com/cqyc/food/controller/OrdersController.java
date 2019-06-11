package com.cqyc.food.controller;


import com.alibaba.fastjson.JSONObject;
import com.cqyc.food.comm.JwtProperties;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.*;
import com.cqyc.food.service.IOrdersService;
import com.cqyc.food.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@RestController
@RequestMapping("/orders")
@EnableConfigurationProperties(JwtProperties.class)
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private JwtProperties prop;

    /**
     * 插入订单后然后再查询
     */
    @GetMapping("/queryOrder")
    public ResponseEntity<List<Orders>> queryOrder(@CookieValue("YC_TOKEN") String token){
//        Buyer buyer = (Buyer) session.getAttribute("buyer");
        try {
            UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            return ResponseEntity.ok(ordersService.queryOrder(info.getAccount()));
        } catch (Exception e) {
            throw new YcException(ExceptionEnums.QUERY_ORDER_ERROR);
        }
    }

    /**
     * fastjson转换
     */
    @PostMapping("/insertOrder")
    public ResponseEntity<Object> insertOrder(@RequestBody String shopCarts){
        //将字符串转换为list对象，然后遍历插入
        List<ShopCart> shopCarts1 = JSONObject.parseArray(shopCarts, ShopCart.class);
//      shopCarts1.forEach(shopCart -> System.out.println("shopCart = " + shopCart));

        return ResponseEntity.ok( ordersService.insertOrder(shopCarts1));
    }

    /**
     * 插入对应的一条数据
     */
    @PostMapping("insertOneOrder")
    public ResponseEntity<Object> insertOneOrder(@RequestBody String food,@CookieValue("YC_TOKEN") String token){
        Food food1 = JSONObject.parseObject(food, Food.class);
//        Buyer buyer = (Buyer) session.getAttribute("buyer");
        try {
            UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            return ResponseEntity.ok(ordersService.insertOneOrder(food1,info.getAccount()));
        } catch (Exception e) {
            throw new YcException(ExceptionEnums.INSERT_ORDER_ERROR);
        }

    }
}
