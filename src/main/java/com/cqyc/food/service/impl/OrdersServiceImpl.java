package com.cqyc.food.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.Food;
import com.cqyc.food.domain.Orders;
import com.cqyc.food.domain.ShopCart;
import com.cqyc.food.mapper.OrdersMapper;
import com.cqyc.food.mapper.ShopCartMapper;
import com.cqyc.food.service.IBuyerService;
import com.cqyc.food.service.IFoodService;
import com.cqyc.food.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private IFoodService foodService;

    @Autowired
    private IBuyerService buyerService;

    @Autowired
    private ShopCartMapper shopCartMapper;
    /**
     * 批量插入订单
     */
    @Transactional
    public Map<String, String> insertOrder(List<ShopCart> shopCarts1) {
        Map<String, String> map = new HashMap<>();
        for (ShopCart shopCart : shopCarts1) {
            Food food = foodService.queryOneFood(Integer.parseInt(shopCart.getFID()));
            Buyer buyer = buyerService.getOne(new QueryWrapper<Buyer>().lambda().eq(Buyer::getAccount, shopCart.getBuyerName()));
            //调用下面的方法
            insertMethod(food,buyer);

            shopCartMapper.deleteById(shopCart.getId());
        }
        map.put("code","200");
        map.put("msg","新增订单成功删除购物车中数据");
        return map;
    }

    /**
     * 查询订单详情
     */
    public List<Orders> queryOrder(String account) {
        List<Orders> orders = ordersMapper.selectList(new QueryWrapper<Orders>().lambda().eq(Orders::getBuyerName, account));
//        orders.forEach(orders1 -> System.out.println("orders1 = " + orders1));
        if(CollectionUtils.isEmpty(orders)){
            throw new YcException(ExceptionEnums.QUERY_ORDER_ERROR);
        }
        return orders;
    }

    /**
     * 插入一条数据
     */
    public Map<String, String> insertOneOrder(Food food,String account) {
        Map<String, String> map = new HashMap<>();
        Buyer buyer = buyerService.getOne(new QueryWrapper<Buyer>().lambda().eq(Buyer::getAccount, account));
        insertMethod(food,buyer);
        map.put("code","200");
        map.put("msg","新增一条数据成功");
        return map;
    }

    private void insertMethod(Food food,Buyer buyer){
        Orders orders = new Orders();
        orders.setFID(food.getFID());
        orders.setBuyerName(buyer.getAccount());
        orders.setFPrice(food.getFPrice());
        //设置需要支付多少钱
        orders.setPayMoney(food.getFPrice());
        orders.setTradingStatus(1);//交易状态
        orders.setDeliveryStatus(0);//发货状态
        orders.setTime(LocalDateTime.now());
        orders.setFName(food.getFName());
        orders.setFDescription(food.getFDescription());
        orders.setFImage(food.getFImage());
        orders.setUserName(buyer.getUserName());
        orders.setAddress(buyer.getAddress());
        int insert = ordersMapper.insert(orders);
        if (insert != 1) {
            throw new YcException(ExceptionEnums.INSERT_ORDER_ERROR);
        }
    }

}
