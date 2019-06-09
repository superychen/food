package com.cqyc.food.mapper;

import com.cqyc.food.domain.Food;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@Repository
public interface FoodMapper extends BaseMapper<Food> {

    List<Food> queryHotFood(@Param("hotLimit") Integer hotLimit);
}
