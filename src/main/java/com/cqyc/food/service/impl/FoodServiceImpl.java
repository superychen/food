package com.cqyc.food.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Food;
import com.cqyc.food.mapper.FoodMapper;
import com.cqyc.food.service.IFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {

    @Autowired
    private FoodMapper foodMapper;
    /**
     * 查询热门食物的个数
     */
    public List<Food> queryHotFood(Integer hotLimit) {
        List<Food> foods = foodMapper.queryHotFood(hotLimit);
        if(CollectionUtils.isEmpty(foods)){
            throw new YcException(ExceptionEnums.HOT_FOOD_QUERY_ERROR);
        }
        return foods;
    }

    /**
     * 分页查询所有食物个数
     */
    public IPage<Food> queryAllFoods(Integer currentPage, Integer pageSize) {
        IPage<Food> foodIPage = foodMapper.selectPage(new Page<>(currentPage, pageSize), new QueryWrapper<>());
        //System.out.println(Collections.unmodifiableCollection(foodIPage.getRecords()));
        if (CollectionUtils.isEmpty(foodIPage.getRecords())) {
            throw new YcException(ExceptionEnums.FOOD_QUERY_ERROR);
        }
        return foodIPage;
    }



    /**
     * 根据食物的id查询食物的详情
     */
    public Food queryOneFood(Integer fID) {
        Food food = foodMapper.selectById(fID);
        if (food == null) {
            throw new YcException(ExceptionEnums.ONE_FOOD_QUERY_ERROR);
        }
        return food;
    }

}
