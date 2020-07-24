package com.vectory.controller;

import com.vectory.enums.YesOrNo;
import com.vectory.pojo.Carousel;
import com.vectory.pojo.Category;
import com.vectory.response.CommonReturnType;
import com.vectory.response.error.EmBusinessResult;
import com.vectory.service.ICarouselService;
import com.vectory.service.ICategoryService;
import com.vectory.vo.CategoryVO;
import com.vectory.vo.NewItemsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "INDEX")
@RestController
@RequestMapping("index")
public class IndexController {

    @Resource
    private ICarouselService carouselServiceImpl;
    @Resource
    private ICategoryService categoryServiceImpl;

    @ApiOperation(value = "GET_CAROUSEL", httpMethod = "GET")
    @GetMapping("carousel")
    public CommonReturnType carousel() {
        List<Carousel> list = carouselServiceImpl.queryAll(YesOrNo.YES.type);
        return CommonReturnType.success(list);
    }

    @ApiOperation(value = "GET_ROOT_CATS", httpMethod = "GET")
    @GetMapping("cats")
    public CommonReturnType cats() {
        List<Category> list = categoryServiceImpl.queryAllRootLevelCat();
        return CommonReturnType.success(list);
    }

    @ApiOperation(value = "GET_CHILD_CATS", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public CommonReturnType subCat(@ApiParam(name = "rootCatId", value = "一级分类id", required = true)
                                       @PathVariable Integer rootCatId) {
        if (rootCatId == null)
            return CommonReturnType.fail(EmBusinessResult.CATEGORY_NOT_EXIST);
        List<CategoryVO> list = categoryServiceImpl.getSubCatList(rootCatId);
        return CommonReturnType.success(list);
    }

    @ApiOperation(value = "SIX_ITEM_FOR_SHOW", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public CommonReturnType sixNewItems(@ApiParam(name = "rootCatId", value = "一级分类id", required = true)
                                      @PathVariable Integer rootCatId) {
        if (rootCatId == null)
            return CommonReturnType.fail(EmBusinessResult.CATEGORY_NOT_EXIST);
        List<NewItemsVO> list = categoryServiceImpl.getSixNewItemsLazy(rootCatId);
        return CommonReturnType.success(list);
    }
}
