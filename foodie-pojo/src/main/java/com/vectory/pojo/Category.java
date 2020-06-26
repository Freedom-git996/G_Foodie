package com.vectory.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 商品分类 
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "category")
public class Category {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 分类类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 父id
     */
    @TableField(value = "father_id")
    private Integer fatherId;

    /**
     * 图标
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 口号
     */
    @TableField(value = "slogan")
    private String slogan;

    /**
     * 分类图
     */
    @TableField(value = "cat_image")
    private String catImage;

    /**
     * 背景颜色
     */
    @TableField(value = "bg_color")
    private String bgColor;
}