package com.cqyc.food.comm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常枚举类
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum  ExceptionEnums {

    LOGIN_USER_ERROR(400,"用户登录失败，请检查"),
    USER_NOT_LOGIN(500,"用户没有登录哟"),
    USER_REGISTER_ERROR(500,"用户注册失败,用户名有可能被注册了哟"),
    HOT_FOOD_QUERY_ERROR(500,"热门食品查询失败"),
    FOOD_QUERY_ERROR(500,"商品查询失败"),
    ONE_FOOD_QUERY_ERROR(500,"商品详情查询失败"),
    ALL_COMMENT_ERROR(500,"评论加载失败"),
    INSERT_COMMENT_ERROR(500,"插入用户评论失败"),
    SHOP_CART_INSERT_ERROR(500,"购物车插入失败哟"),
    SHOP_CART_QUERY_ERROR(500,"购物车查询失败"),
    INSERT_ORDER_ERROR(500,"增加订单失败"),
    QUERY_ORDER_ERROR(500,"订单查询失败，或者没有订单"),
    USER_LOGIN_ERROR(500,"用户登录凭证失败,检查cookie是否被删除"),
    USER_LOGIN_PASSWORD_ERROR(500,"用户登录时密码错误"),
    UN_AUTHORIZED(403,"未授权"),
    ;
    private int code;
    private String msg;
}
