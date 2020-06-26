package com.vectory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vectory.mapper.CarouselMapper;
import com.vectory.pojo.Carousel;
import com.vectory.service.ICarouselService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarouselServiceImpl implements ICarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
        carouselQueryWrapper.eq("is_show", isShow);
        carouselQueryWrapper.orderByDesc("sort");
        return carouselMapper.selectList(carouselQueryWrapper);
    }
}
