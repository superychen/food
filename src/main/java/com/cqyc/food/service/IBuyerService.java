package com.cqyc.food.service;

import com.cqyc.food.domain.Buyer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
public interface IBuyerService extends IService<Buyer> {

    Buyer userLogin(String loginName);

    void userRegister(Buyer buyer);

    String userToken(String loginName, String userPassword) throws Exception;
}
