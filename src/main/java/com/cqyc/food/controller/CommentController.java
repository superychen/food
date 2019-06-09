package com.cqyc.food.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.Comment;
import com.cqyc.food.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentController {

    @Autowired
    private ICommentService commentService;

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
    public ResponseEntity<Object> insertComment(String youComment, String fID, HttpSession session){
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        commentService.insertComment(youComment,fID,buyer.getAccount());
        Map<String,String> map = new HashMap<>();
        map.put("code",String.valueOf(HttpStatus.CREATED));
        return ResponseEntity.ok(map);
    }


}
