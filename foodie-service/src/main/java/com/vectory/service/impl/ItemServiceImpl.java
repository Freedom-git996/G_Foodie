package com.vectory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vectory.qo.CatItemQO;
import com.vectory.qo.ItemCommentQO;
import com.vectory.qo.SearchItemQO;
import com.vectory.enums.CommentLevel;
import com.vectory.enums.YesOrNo;
import com.vectory.mapper.*;
import com.vectory.pojo.*;
import com.vectory.response.error.BusinessException;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.IItemService;
import com.vectory.vo.PagedGridResultVO;
import com.vectory.vo.CommentCountsVO;
import com.vectory.vo.ItemCommentVO;
import com.vectory.vo.SearchItemsVO;
import com.vectory.vo.ShopcartVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ItemServiceImpl extends BaseServiceImpl implements IItemService {

    @Resource
    private ItemsMapper itemsMapper;
    @Resource
    private ItemsImgMapper itemsImgMapper;
    @Resource
    private ItemsSpecMapper itemsSpecMapper;
    @Resource
    private ItemsParamMapper itemsParamMapper;
    @Resource
    private ItemsCommentsMapper itemsCommentsMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectById(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        // 查询指定商品的图片
        QueryWrapper<ItemsImg> itemsImgQueryWrapper = new QueryWrapper<>();
        itemsImgQueryWrapper.eq("item_id", itemId);
        return itemsImgMapper.selectList(itemsImgQueryWrapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        // 查询指定商品规格
        QueryWrapper<ItemsSpec> itemsSpecQueryWrapper = new QueryWrapper<>();
        itemsSpecQueryWrapper.eq("item_id", itemId);
        return itemsSpecMapper.selectList(itemsSpecQueryWrapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        // 查询指定产品参数
        QueryWrapper<ItemsParam> itemsParamQueryWrapper = new QueryWrapper<>();
        itemsParamQueryWrapper.eq("item_id", itemId);
        return itemsParamMapper.selectOne(itemsParamQueryWrapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);
        Integer totalCounts = goodCounts + normalCounts + badCounts;
        CommentCountsVO countsVO = new CommentCountsVO();
        countsVO.setTotalCounts(totalCounts);
        countsVO.setGoodCounts(goodCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setBadCounts(badCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId, Integer level) {
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if (level != null) {
            condition.setCommentLevel(level);
        }
        QueryWrapper<ItemsComments> itemsCommentsQueryWrapper = new QueryWrapper<>();
        itemsCommentsQueryWrapper.eq("item_id", itemId);
        itemsCommentsQueryWrapper.eq("comment_level", level);
        return itemsCommentsMapper.selectCount(itemsCommentsQueryWrapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResultVO queryPagedComments(ItemCommentQO itemCommentQO) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemCommentQO.getItemId());
        map.put("level", itemCommentQO.getLevel());
        IPage<ItemCommentVO> itemCommentVOIPage = itemsMapper.queryItemCommentsPlus(
                new Page<>(itemCommentQO.getPageIndex(), itemCommentQO.getPageSize()), map);
        return setterPagedGrid(itemCommentVOIPage.getRecords(), Integer.parseInt(String.valueOf(itemCommentVOIPage.getCurrent())));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResultVO searhItems(SearchItemQO searchItemQO) {
        Map<String, Object> map = new HashMap<>();
        map.put("keywords", searchItemQO.getKeywords());
        map.put("sort", searchItemQO.getSort());
        IPage<SearchItemsVO> searchItemsVOIPage = itemsMapper.searchItemsPlus(
                new Page<>(searchItemQO.getPageIndex(), searchItemQO.getPageSize()), map);
        return setterPagedGrid(searchItemsVOIPage.getRecords(), Integer.parseInt(String.valueOf(searchItemsVOIPage.getCurrent())));
    }

    @Override
    public PagedGridResultVO searhItems(CatItemQO catItemQO) {
        Map<String, Object> map = new HashMap<>();
        map.put("catId", catItemQO.getCatId());
        map.put("sort", catItemQO.getSort());
        IPage<SearchItemsVO> searchItemsVOIPage = itemsMapper.searchItemsByThirdCatPlus(
                new Page<>(catItemQO.getPageIndex(), catItemQO.getPageSize()), map);
        return setterPagedGrid(searchItemsVOIPage.getRecords(), Integer.parseInt(String.valueOf(searchItemsVOIPage.getCurrent())));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartVO> queryItemsBySpecIds(String specIds) {
        String[] ids = specIds.split(",");
        List<String> specIdsList = new ArrayList<>();
        Collections.addAll(specIdsList, ids);
        return itemsMapper.queryItemsBySpecIds(specIdsList);
    }

    @Override
    public ItemsSpec queryItemSpecById(String specId) {
        return itemsSpecMapper.selectById(specId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String queryItemMainImgById(String itemId) {
        QueryWrapper<ItemsImg> itemsImgQueryWrapper = new QueryWrapper<>();
        itemsImgQueryWrapper.eq("item_id", itemId);
        itemsImgQueryWrapper.eq("is_main", YesOrNo.YES.type);
        ItemsImg itemsImg = itemsImgMapper.selectOne(itemsImgQueryWrapper);
        return itemsImg != null ? itemsImg.getUrl() : "";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void decreaseItemSpecStock(String specId, int buyCounts) {
        int result = itemsMapper.decreaseItemSpecStock(specId, buyCounts);
        if (result != 1) {
            throw new BusinessException(EmBusinessResult.ORDER_CREATE_FAIL);
        }
    }
}
