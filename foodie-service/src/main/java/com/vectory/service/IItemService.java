package com.vectory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vectory.pojo.Items;
import com.vectory.pojo.ItemsImg;
import com.vectory.pojo.ItemsParam;
import com.vectory.pojo.ItemsSpec;
import com.vectory.utils.PagedGridResult;
import com.vectory.vo.CommentLevelCountsVO;
import com.vectory.vo.ItemCommentVO;
import com.vectory.vo.SearchItemsVO;
import com.vectory.vo.ShopcartVO;

import java.util.List;

public interface IItemService {

    /**
     * 根据商品ID查询详情
     * @param itemId itemId
     * @return Items
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId itemId
     * @return List
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId itemId
     * @return List
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId itemId
     * @return ItemsParam
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品id查询商品的评价等级数量
     * @param itemId itemId
     * @return CommentLevelCountsVO
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品的评价（分页）
     * @param itemId itemId
     * @param level level
     * @return PagedGridResult
     */
    PagedGridResult queryPagedComments(String itemId, Integer level,
                                       Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     * @param keywords keywords
     * @param sort sort
     * @param page page
     * @param pageSize pageSize
     * @return PagedGridResult
     */
    PagedGridResult searhItems(String keywords, String sort,
                                   Integer page, Integer pageSize);

    /**
     * 根据分类id搜索商品列表
     * @param catId catId
     * @param sort sort
     * @param page page
     * @param pageSize pageSize
     * @return PagedGridResult
     */
    PagedGridResult searhItems(Integer catId, String sort,
                                      Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中商品数据（用于刷新渲染购物车中的商品数据）
     * @param specIds specIds
     * @return List
     */
    List<ShopcartVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId specId
     * @return ItemsSpec
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品id获得商品图片主图url
     * @param itemId itemId
     * @return String
     */
    String queryItemMainImgById(String itemId);

    /**
     * 减少库存
     * @param specId specId
     * @param buyCounts buyCounts
     */
    void decreaseItemSpecStock(String specId, int buyCounts);
}
