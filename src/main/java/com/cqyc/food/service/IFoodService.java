package com.cqyc.food.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqyc.food.domain.Food;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
public interface IFoodService extends IService<Food> {

    List<Food> queryHotFood(Integer hotLimit);

    IPage<Food> queryAllFoods(Integer currentPage, Integer pageSize);

    Food queryOneFood(Integer fID);
}
