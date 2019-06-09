package com.cqyc.food.controller;


import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.ShopCart;
import com.cqyc.food.service.IShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

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
public class ShopCartController {

    @Autowired
    private IShopCartService shopCartService;

    /**
     * 插入购物车
     */
    @PostMapping("insertShopCart")
    public ResponseEntity<Object> insertShopCart(@RequestParam("fID") String fID, HttpSession session){
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        if (buyer == null) {
            throw new YcException(ExceptionEnums.USER_NOT_LOGIN);
        }
        return ResponseEntity.ok(shopCartService.insertShopCart(fID,buyer.getAccount()));
    }


    /**
     * 查询购物车
     */
    @GetMapping("/queryShopCart")
    public ResponseEntity<List<ShopCart>> queryShopCart(HttpSession session){
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        return ResponseEntity.ok(shopCartService.queryShopCart(buyer.getAccount()));
    }

}
