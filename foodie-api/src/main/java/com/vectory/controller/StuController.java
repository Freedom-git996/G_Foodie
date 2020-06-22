package com.vectory.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vectory.mapper.StuMapper;
import com.vectory.pojo.Stu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StuController {

    @Resource
    private StuMapper stuMapper;

    @GetMapping(value = "/get")
    public String select() {
        QueryWrapper<Stu> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "xiaobai");
        List<Stu> stus = stuMapper.selectList(wrapper);
        return stus.toString();
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }
}
