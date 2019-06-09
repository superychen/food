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
    public class Food extends Model<Food> {

    private static final long serialVersionUID = 1L;

            /**
            * 商品ID
            */
            @TableId(value = "fID", type = IdType.AUTO)
    private String fID;

            /**
            * 商品名称
            */
        @TableField("fName")
    private String fName;

            /**
            * 详细说明
            */
        @TableField("fDescription")
    private String fDescription;

            /**
            * 菜品类型(中餐or西餐)
            */
        @TableField("fType")
    private String fType;

            /**
            * 菜品口味
            */
        @TableField("fHobbies")
    private String fHobbies;

            /**
            * 工艺(煎、炒、炖、焖、烤、炸、蒸)
            */
        @TableField("fTechnology")
    private String fTechnology;

            /**
            * 季节推荐(春，夏，秋，冬)
            */
        @TableField("fSeasonal")
    private String fSeasonal;

            /**
            * 商品价格
            */
        @TableField("fPrice")
    private Double fPrice;

            /**
            * 图片地址
            */
        @TableField("fImage")
    private String fImage;

            /**
            * 销量
            */
        @TableField("fSalesVolume")
    private Integer fSalesVolume;


    @Override
    protected Serializable pkVal() {
        return this.fID;
    }

}
