package com.vectory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vectory.pojo.ItemsComments;
import com.vectory.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ItemsCommentsMapper extends BaseMapper<ItemsComments> {

    void saveComments(Map<String, Object> map);

    IPage<MyCommentVO> queryMyComments(Page page, @Param("paramsMap") Map<String, Object> map);
}