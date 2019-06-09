package com.cqyc.food.domain;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;
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
    public class ShopCart extends Model<ShopCart> {

    private static final long serialVersionUID = 1L;

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

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("fName")
    private String fName;

    @TableField("fImage")
    private String fImage;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
