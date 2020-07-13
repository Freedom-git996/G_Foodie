package com.vectory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MySubOrderItemVO {

    private String itemId;
    private String itemImg;
    private String itemName;
    private String itemSpecName;
    private Integer buyCounts;
    private Integer price;
}
