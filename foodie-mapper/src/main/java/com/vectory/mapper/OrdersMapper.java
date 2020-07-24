package com.vectory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vectory.pojo.OrderStatus;
import com.vectory.pojo.Orders;
import com.vectory.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface OrdersMapper extends BaseMapper<Orders> {

    IPage<MyOrdersVO> queryMyOrders(Page page, @Param("paramsMap") Map<String, Object> map);

    int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    IPage<OrderStatus> getMyOrderTrend(Page page, @Param("paramsMap") Map<String, Object> map);
}