package com.vectory.service.impl;

import com.github.pagehelper.PageInfo;
import com.vectory.utils.PagedGridResult;

import java.util.List;

public class BaseServiceImpl {

    public PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
