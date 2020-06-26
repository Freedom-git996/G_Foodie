package com.vectory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vectory.pojo.Category;
import com.vectory.vo.CategoryVO;
import com.vectory.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVO> getSubCatList(Integer rootCatId);

    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}