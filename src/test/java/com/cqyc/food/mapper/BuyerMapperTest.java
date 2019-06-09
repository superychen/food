package com.cqyc.food.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqyc.food.comm.MD5Hash;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.Comment;
import com.cqyc.food.domain.ShopCart;
import com.cqyc.food.service.IBuyerService;
import com.cqyc.food.service.ICommentService;
import com.cqyc.food.service.IShopCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author:
 * @Date:
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BuyerMapperTest {
   /* @Autowired
    private IBuyerService buyerService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IShopCartService shopCartService;

    @Test
    public void loginUser(){
        Buyer buyer = new Buyer();
        buyer.setUserName("admin");
        buyer.setPassword("123");
        Buyer buyer1 = buyerService.userLogin(buyer.getUserName());
        System.out.println("buyer1.getPassword() = " + buyer1.getUserName());
    }


    @Test
    public void testMD5(){
        System.out.println(MD5Hash.addSalt("123456","admin"));
    }

    //测试查询评论数量
    @Test
    public void queryCommentCount(){
        System.out.println(commentService.queryCommentCount("1"));

    }

    //测试所有评论
    @Test
    public void queryAllComment(){
        IPage<Comment> commentIPage = commentService.queryAllComments(1, 10, "1");
        System.out.println(commentIPage.getRecords());
    }

    @Test
    public void queryShopCart(){
        List<ShopCart> shopCarts = shopCartService.queryShopCart("admin");
        shopCarts.forEach(shopCart -> System.out.println("shopCart = " + shopCart));
    }*/

}
