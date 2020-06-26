package com.vectory.service;

import com.vectory.pojo.Category;
import com.vectory.vo.CategoryVO;
import com.vectory.vo.NewItemsVO;

import java.util.List;

public interface ICategoryService {

    /**
     * 查询所有一级分类
     * @return List
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId rootCatId
     * @return List
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId rootCatId
     * @return List
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
