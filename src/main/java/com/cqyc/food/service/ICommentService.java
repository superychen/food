package com.cqyc.food.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqyc.food.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cqyc
 * @since 2019-06-04
 */
public interface ICommentService extends IService<Comment> {

    Integer queryCommentCount(String fID);

    IPage<Comment> queryAllComments(Integer currentPage, Integer pageSize,String fID);

    void insertComment(String youComment, String fID, String account);
}
