package com.cqyc.food.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqyc.food.comm.JwtProperties;
import com.cqyc.food.comm.MD5Hash;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.UserInfo;
import com.cqyc.food.mapper.BuyerMapper;
import com.cqyc.food.service.IBuyerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqyc.food.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@Service
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, Buyer> implements IBuyerService {
    @Autowired
    private BuyerMapper buyerMapper;

    @Autowired
    private JwtProperties prop;

    /**
     * 查询用户
     */
    public Buyer userLogin(String loginName) {
        Buyer buyer = buyerMapper.selectOne(new QueryWrapper<Buyer>().lambda().eq(Buyer::getAccount, loginName));
        return buyer;
    }

    /**
     * 用户注册
     */
    @Override
    @Transactional
    public void userRegister(Buyer buyer) {
        System.out.println(buyer.getAccount() +" : "+buyer.getPassword());
        buyer.setPassword(MD5Hash.addSalt(buyer.getPassword(),buyer.getAccount()));
        System.out.println("buyer.getPassword() = " + buyer.getPassword());
        int count = buyerMapper.insert(buyer);
        if(count != 1){
            throw new YcException(ExceptionEnums.USER_REGISTER_ERROR);
        }
    }

    /**
     * 使用jwt来写入用户到cookie中
     */
    public String userToken(String loginName, String userPassword) throws Exception {
            //校验
            Buyer buyer = buyerMapper.selectOne(new QueryWrapper<Buyer>().lambda().eq(Buyer::getAccount, loginName));
            if (buyer == null) {
                throw new YcException(ExceptionEnums.LOGIN_USER_ERROR);
            }
            if (!StringUtils.equals(buyer.getPassword(),MD5Hash.addSalt(userPassword,loginName))) {
                throw new YcException(ExceptionEnums.USER_LOGIN_PASSWORD_ERROR);
            }
            //生成token
            String token = JwtUtils.generateToken(new UserInfo(buyer.getAccount(),buyer.getUserName()),prop.getPrivateKey(),prop.getExpire());
            log.debug("service层生成的token：{}",token);
            return token;
    }
}
