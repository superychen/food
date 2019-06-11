package com.cqyc.food.controller;


import com.cqyc.food.comm.JwtProperties;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.ShopCart;
import com.cqyc.food.domain.UserInfo;
import com.cqyc.food.service.IShopCartService;
import com.cqyc.food.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@Controller
@RequestMapping("/shop-cart")
@EnableConfigurationProperties(JwtProperties.class)
public class ShopCartController {

    @Autowired
    private IShopCartService shopCartService;

    @Autowired
    private JwtProperties prop;

    /**
     * 插入购物车
     */
    @PostMapping("insertShopCart")
    public ResponseEntity<Object> insertShopCart(@RequestParam("fID") String fID, @CookieValue("YC_TOKEN") String token){
//        Buyer buyer = (Buyer) session.getAttribute("buyer");
        try {
            UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            if (info == null) {
                throw new YcException(ExceptionEnums.USER_NOT_LOGIN);
            }
            return ResponseEntity.ok(shopCartService.insertShopCart(fID,info.getAccount()));
        } catch (Exception e) {
            throw new YcException(ExceptionEnums.SHOP_CART_INSERT_ERROR);
        }
        
    }


    /**
     * 查询购物车
     */
    @GetMapping("/queryShopCart")
    public ResponseEntity<List<ShopCart>> queryShopCart(@CookieValue("YC_TOKEN") String token){
//        Buyer buyer = (Buyer) session.getAttribute("buyer");
        try {
            UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            return ResponseEntity.ok(shopCartService.queryShopCart(info.getAccount()));
        } catch (Exception e) {
            throw new YcException(ExceptionEnums.SHOP_CART_QUERY_ERROR);
        }
    }

}
