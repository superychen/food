package com.cqyc.food.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqyc.food.comm.exception.ExceptionEnums;
import com.cqyc.food.comm.exception.YcException;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.domain.Comment;
import com.cqyc.food.domain.Food;
import com.cqyc.food.mapper.CommentMapper;
import com.cqyc.food.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    //根据食物id查询食物的评论
    public Integer queryCommentCount(String fID) {
        Comment comment = new Comment();
        comment.setFID(fID);
        Integer count = commentMapper.selectCount(new QueryWrapper<Comment>().setEntity(comment));
        return count;
    }

    //查询所有的评论，分页
    public IPage<Comment> queryAllComments(Integer currentPage, Integer pageSize,String fID) {
        IPage<Comment> comments = commentMapper.selectPage(new Page<>(currentPage, pageSize),
                new QueryWrapper<Comment>().setEntity(new Comment().setFID(fID)));
//        System.out.println(Collections.unmodifiableCollection(comments.getRecords()));
        if(CollectionUtils.isEmpty(comments.getRecords())){
            throw new YcException(ExceptionEnums.ALL_COMMENT_ERROR);
        }
        return comments;
    }

    /**
     * 插入用户
     */
    @Transactional
    public void insertComment(String youComment, String fID, String account) {
        Comment comment = new Comment();
        comment.setFID(fID);
        comment.setComment(youComment);
        comment.setTime(LocalDate.now());
        comment.setBuyerName(account);
        int insert = commentMapper.insert(comment);
        if(insert != 1){
            throw new YcException(ExceptionEnums.INSERT_COMMENT_ERROR);
        }
    }
}
