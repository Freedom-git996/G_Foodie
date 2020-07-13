package com.vectory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vectory.pojo.OrderStatus;
import com.vectory.pojo.Orders;
import com.vectory.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapper extends BaseMapper<Orders> {

    List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);

    int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);
}