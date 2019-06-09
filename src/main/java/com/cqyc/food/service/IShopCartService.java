package com.cqyc.food.service;

import com.cqyc.food.domain.ShopCart;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.shiro.authc.Account;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
public interface IShopCartService extends IService<ShopCart> {

    Map<String,String> insertShopCart(String fID, String account);

    List<ShopCart> queryShopCart(String account);
}
