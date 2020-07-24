package com.vectory.service.impl;

import com.github.pagehelper.PageInfo;
import com.vectory.vo.PagedGridResultVO;

import java.util.List;

public class BaseServiceImpl {

    public PagedGridResultVO setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResultVO grid = new PagedGridResultVO();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
