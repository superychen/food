package com.cqyc.food.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqyc.food.domain.Food;
import com.cqyc.food.service.IFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@RestController
@Slf4j
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private IFoodService foodService;

    /**
     * 查询热门食品，限制为4个
     */
    @GetMapping("/hotfood")
    public ResponseEntity<List<Food>> queryHotFood(@RequestParam("hotLimit") Integer hotLimit){
        return ResponseEntity.ok(foodService.queryHotFood(hotLimit));
    }

    /**
     * 查询所有商品根据分页来查询
     */
    @GetMapping("/allFoods")
    public ResponseEntity<IPage<Food>> queryAllFoods(Integer currentPage,Integer pageSize){
        return  ResponseEntity.ok(foodService.queryAllFoods(currentPage,pageSize));
    }


    /**
     * 根据食物的id查询食物的详情
     */
    @GetMapping("/oneFood")
    public ResponseEntity<Food> queryOneFood(Integer fID){
        return ResponseEntity.ok(foodService.queryOneFood(fID));
    }
}
