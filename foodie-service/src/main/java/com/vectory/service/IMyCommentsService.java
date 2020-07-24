package com.vectory.service;

import com.vectory.qo.OrderItemCommentQO;
import com.vectory.qo.QueryMyCommentQO;
import com.vectory.pojo.OrderItems;
import com.vectory.vo.PagedGridResultVO;

import java.util.List;

public interface IMyCommentsService {

    /**
     * 根据订单id查询关联的商品
     * @param orderId orderId
     * @return List
     */
    List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     * @param orderId orderId
     * @param userId userId
     * @param commentList commentList
     */
    void saveComments(String orderId, String userId, List<OrderItemCommentQO> commentList);


    /**
     * 我的评价查询 分页
     * @param queryMyCommentQO queryMyCommentQO
     * @return PagedGridResultVO
     */
    PagedGridResultVO queryMyComments(QueryMyCommentQO queryMyCommentQO);
}
