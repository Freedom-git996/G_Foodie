package com.vectory.service;

import com.vectory.pojo.Carousel;

import java.util.List;

public interface ICarouselService {

    /**
     * 查询所有轮播图列表
     * @param isShow isShow
     * @return List
     */
    List<Carousel> queryAll(Integer isShow);
}
