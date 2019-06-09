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
public class Buyer extends Model<Buyer> {

    private static final long serialVersionUID = 1L;

    /**
    * 用户账号
    */
    private String account;

    /**
    * 密码
    */
    private String password;

    /**
    * 收货地址
    */
    private String address;

    /**
    * 联系电话号码
    */
    private String tel;

    @TableField("userName")
    private String userName;


    @Override
    protected Serializable pkVal() {
        return this.account;
    }

}
