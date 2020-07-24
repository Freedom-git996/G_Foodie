package com.vectory.controller;

import com.vectory.qo.CatItemQO;
import com.vectory.qo.ItemCommentQO;
import com.vectory.qo.SearchItemQO;
import com.vectory.pojo.Items;
import com.vectory.pojo.ItemsImg;
import com.vectory.pojo.ItemsParam;
import com.vectory.pojo.ItemsSpec;
import com.vectory.response.CommonReturnType;
import com.vectory.service.IItemService;
import com.vectory.util.validator.ValidatorUtil;
import com.vectory.vo.PagedGridResultVO;
import com.vectory.vo.CommentCountsVO;
import com.vectory.vo.ItemInfoVO;
import com.vectory.vo.ShopcartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "ITEMS")
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {

    @Resource
    private IItemService itemService;

    @ApiOperation(value = "ITEM_INFO", httpMethod = "GET")
    @GetMapping("info")
    public CommonReturnType info(@ApiParam(name = "itemId", value = "商品ID", required = true)
                                     @RequestParam String itemId) {
        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemSpecList(itemsSpecList);
        itemInfoVO.setItemParams(itemsParam);

        return CommonReturnType.success(itemInfoVO);
    }

    @ApiOperation(value = "ITEM_COMMENTS_LEVEL", httpMethod = "GET")
    @GetMapping("commentLevel")
    public CommonReturnType commentLevel(@ApiParam(name = "itemId", value = "商品id", required = true)
                                             @RequestParam String itemId) {
        CommentCountsVO commentCountsVO = itemService.queryCommentCounts(itemId);
        return CommonReturnType.success(commentCountsVO);
    }

    @ApiOperation(value = "ITEM_COMMENTS", httpMethod = "GET")
    @GetMapping("comments")
    public CommonReturnType comments(ItemCommentQO itemCommentQO) {
        ValidatorUtil.validate(itemCommentQO);
        PagedGridResultVO pagedGridResultVO = itemService.queryPagedComments(itemCommentQO);
        return CommonReturnType.success(pagedGridResultVO);
    }

    @ApiOperation(value = "ITEM_SEARCH", httpMethod = "GET")
    @GetMapping("search")
    public CommonReturnType search(@ModelAttribute SearchItemQO searchItemQO) {
        ValidatorUtil.validate(searchItemQO);
        PagedGridResultVO pagedGridResultVO = itemService.searhItems(searchItemQO);
        return CommonReturnType.success(pagedGridResultVO);
    }

    @ApiOperation(value = "ITEMS_CAT", httpMethod = "GET")
    @GetMapping("catItems")
    public CommonReturnType catItems(CatItemQO catItemQO) {
        ValidatorUtil.validate(catItemQO);
        PagedGridResultVO pagedGridResultVO = itemService.searhItems(catItemQO);
        return CommonReturnType.success(pagedGridResultVO);
    }

    // 用于用户长时间未登录网站，刷新购物车中的数据（主要是商品价格），类似京东淘宝
    @ApiOperation(value = "ITEM_RRFRESH", httpMethod = "GET")
    @GetMapping("refresh")
    public CommonReturnType refresh(
            @ApiParam(name = "itemSpecIds", value = "拼接的规格IDs", required = true)
            @RequestParam String itemSpecIds) {
        List<ShopcartVO> shopcartVOList = itemService.queryItemsBySpecIds(itemSpecIds);
        return CommonReturnType.success(shopcartVOList);
    }
}
