package com.cqyc.food.domain;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
    import org.springframework.format.annotation.DateTimeFormat;

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
    public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

            /**
            * 点评ID
            */
            @TableId(value = "cID", type = IdType.AUTO)
    private Integer cID;

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
            * 评价内容
            */
    private String comment;

            /**
            * 点评时间
            */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate time;


    @Override
    protected Serializable pkVal() {
        return this.cID;
    }

}
