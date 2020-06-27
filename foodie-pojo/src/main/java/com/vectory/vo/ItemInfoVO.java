package com.vectory.vo;

import com.vectory.pojo.Items;
import com.vectory.pojo.ItemsImg;
import com.vectory.pojo.ItemsParam;
import com.vectory.pojo.ItemsSpec;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoVO {

    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
