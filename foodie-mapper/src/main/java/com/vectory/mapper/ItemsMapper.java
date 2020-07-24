package com.vectory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vectory.pojo.Items;
import com.vectory.vo.ItemCommentVO;
import com.vectory.vo.SearchItemsVO;
import com.vectory.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapper extends BaseMapper<Items> {

    IPage<ItemCommentVO> queryItemCommentsPlus(Page page, @Param("paramsMap") Map<String, Object> map);

    IPage<SearchItemsVO> searchItemsPlus(Page page, @Param("paramsMap") Map<String, Object> map);

    IPage<SearchItemsVO> searchItemsByThirdCatPlus(Page page, @Param("paramsMap") Map<String, Object> map);

    List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);

    int decreaseItemSpecStock(@Param("specId") String specId,
                              @Param("pendingCounts") int pendingCounts);
}