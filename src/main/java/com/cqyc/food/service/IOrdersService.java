package com.cqyc.food.service;

import com.cqyc.food.domain.Food;
import com.cqyc.food.domain.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqyc.food.domain.ShopCart;

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
public interface IOrdersService extends IService<Orders> {

    Map<String,String> insertOrder(List<ShopCart> shopCarts1);

    List<Orders> queryOrder(String account);

    Map<String,String> insertOneOrder(Food food1,String account);
}
