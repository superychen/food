package com.cqyc.food.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqyc.food.comm.JwtProperties;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.Comment;
import com.cqyc.food.domain.UserInfo;
import com.cqyc.food.service.ICommentService;
import com.cqyc.food.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@RestController
@RequestMapping("/comment")
@EnableConfigurationProperties(JwtProperties.class)
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private JwtProperties prop;

    /**
     * 根据食物id查询食物的评论
     */
    @GetMapping("/commentCount")
    public ResponseEntity<Integer> queryCommentCount(@RequestParam("fID") String fID){
        return ResponseEntity.ok(commentService.queryCommentCount(fID));
    }

    /**
     * 查询所有的评论，并分页
     */
    @GetMapping("/allComments")
    public ResponseEntity<IPage<Comment>>  queryAllComments(Integer currentPage,Integer pageSize,String fID){
        return ResponseEntity.ok(commentService.queryAllComments(currentPage,pageSize,fID));
    }

    /**
     * 插入【评论
     */
    @PostMapping("/insertComment")
    public ResponseEntity<Object> insertComment(String youComment, String fID,@CookieValue("YC_TOKEN") String token){
//        Buyer buyer = (Buyer) session.getAttribute("buyer");
        if(StringUtils.isBlank(token)){
            //如果没有token，证明没有登录，返回500
            throw new YcException(ExceptionEnums.USER_NOT_LOGIN);
        }
        try {
            UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            commentService.insertComment(youComment,fID,info.getAccount());
            Map<String,String> map = new HashMap<>();
            map.put("code",String.valueOf(HttpStatus.CREATED));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new YcException(ExceptionEnums.USER_NOT_LOGIN);
        }

    }


}
