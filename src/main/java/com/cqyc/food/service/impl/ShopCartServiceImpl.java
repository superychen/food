package com.cqyc.food.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Food;
import com.cqyc.food.domain.ShopCart;
import com.cqyc.food.mapper.ShopCartMapper;
import com.cqyc.food.service.IFoodService;
import com.cqyc.food.service.IShopCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartMapper, ShopCart> implements IShopCartService {

    @Autowired
    private ShopCartMapper shopCartMapper;

    @Autowired
    private IFoodService foodService;

    private static final String CODE = "10086";

    /**
     * 插入购物车中的数据
     */
    public Map<String, String> insertShopCart(String fID,String account) {
        Food food = foodService.queryOneFood(Integer.parseInt(fID));
        ShopCart shopCart = new ShopCart();
        shopCart.setFID(fID);
        shopCart.setBuyerName(account);
        shopCart.setFImage(food.getFImage());
        shopCart.setFName(food.getFName());
        int insert = shopCartMapper.insert(shopCart);
        if (insert != 1) {
            throw new YcException(ExceptionEnums.SHOP_CART_INSERT_ERROR);
        }
        Map<String, String> map = new HashMap<>();
        map.put("code",CODE);
        return map;
    }

    /**
     * 查询当前登录用户的购物车信息
     */
    public List<ShopCart> queryShopCart(String account) {
        List<ShopCart> shopCarts = shopCartMapper.selectList(new QueryWrapper<ShopCart>().lambda().eq(ShopCart::getBuyerName, account));
        if (CollectionUtils.isEmpty(shopCarts)) {
            throw new YcException(ExceptionEnums.SHOP_CART_QUERY_ERROR);
        }
        return shopCarts;
    }
}
