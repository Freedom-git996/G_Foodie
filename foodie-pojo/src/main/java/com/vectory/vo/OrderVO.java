package com.vectory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;
}