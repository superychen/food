package com.cqyc.food.domain;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author cqyc
* @since 2019-06-04
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

            /**
            * 订单ID
            */
            @TableId(value = "orderID", type = IdType.AUTO)
    private Integer orderID;

            /**
            * 商品ID
            */
        @TableField("fID")
    private String fID;

            /**
            * 用户账号
            */
        @TableField("buyerName")
    private String buyerName;

            /**
            * 商品价格
            */
        @TableField("fPrice")
    private Double fPrice;

            /**
            * 付款金额
            */
        @TableField("payMoney")
    private Double payMoney;

            /**
            * 交易状态
            */
        @TableField("tradingStatus")
    private Integer tradingStatus;

            /**
            * 发货状态
            */
        @TableField("deliveryStatus")
    private Integer deliveryStatus;

            /**
            * 下单时间
            */
    private LocalDateTime time;

        @TableField("fName")
    private String fName;

        @TableField("fDescription")
    private String fDescription;

        @TableField("fImage")
    private String fImage;

        @TableField("userName")
    private String userName;

    private String address;

    private String tel;


    @Override
    protected Serializable pkVal() {
        return this.orderID;
    }

}
