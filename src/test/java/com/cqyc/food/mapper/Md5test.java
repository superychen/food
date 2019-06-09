package com.cqyc.food.mapper;

import com.cqyc.food.comm.MD5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author:
 * @Date:
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class Md5test {

//    @Test
    public void testMd5(){
        System.out.println(MD5Hash.addSalt("123456","cqyc11"));
    }
}
