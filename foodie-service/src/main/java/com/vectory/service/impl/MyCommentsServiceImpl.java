package com.vectory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vectory.qo.OrderItemCommentQO;
import com.vectory.qo.QueryMyCommentQO;
import com.vectory.enums.YesOrNo;
import com.vectory.mapper.ItemsCommentsMapper;
import com.vectory.mapper.OrderItemsMapper;
import com.vectory.mapper.OrderStatusMapper;
import com.vectory.mapper.OrdersMapper;
import com.vectory.pojo.OrderItems;
import com.vectory.pojo.OrderStatus;
import com.vectory.pojo.Orders;
import com.vectory.service.IMyCommentsService;
import com.vectory.vo.PagedGridResultVO;
import com.vectory.vo.MyCommentVO;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyCommentsServiceImpl extends BaseServiceImpl implements IMyCommentsService {

    @Resource
    public OrderItemsMapper orderItemsMapper;
    @Resource
    public OrdersMapper ordersMapper;
    @Resource
    public OrderStatusMapper orderStatusMapper;
    @Resource
    public ItemsCommentsMapper itemsCommentsMapper;
    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        QueryWrapper<OrderItems> orderItemsQueryWrapper = new QueryWrapper<>();
        orderItemsQueryWrapper.eq("order_id", orderId);
        return orderItemsMapper.selectList(orderItemsQueryWrapper);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveComments(String orderId, String userId, List<OrderItemCommentQO> commentList) {
        for (OrderItemCommentQO oic : commentList) {
            oic.setCommentId(sid.nextShort());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentList", commentList);
        itemsCommentsMapper.saveComments(map);
        Orders order = new Orders();
        order.setId(orderId);
        order.setIsComment(YesOrNo.YES.type);
        ordersMapper.updateById(order);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateById(orderStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResultVO queryMyComments(QueryMyCommentQO queryMyCommentQO) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", queryMyCommentQO.getUserId());
        IPage<MyCommentVO> myCommentVOIPage = itemsCommentsMapper.queryMyComments(
                new Page<>(queryMyCommentQO.getPageIndex(), queryMyCommentQO.getPageSize()), map);
        return setterPagedGrid(myCommentVOIPage.getRecords(), Integer.parseInt(String.valueOf(myCommentVOIPage.getCurrent())));
    }
}
