package com.cqyc.food.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqyc.food.comm.MD5Hash;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.mapper.BuyerMapper;
import com.cqyc.food.service.IBuyerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, Buyer> implements IBuyerService {
    @Autowired
    private BuyerMapper buyerMapper;

    /**
     * 查询用户
     */
    public Buyer userLogin(String loginName) {
        Buyer buyer = buyerMapper.selectOne(new QueryWrapper<Buyer>().lambda().eq(Buyer::getAccount, loginName));

        /*if (!StringUtils.equals(buyer.getPassword(),userPassword)) {
            throw new YcException(ExceptionEnums.LOGIN_USER_ERROR);
        }*/
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



}
