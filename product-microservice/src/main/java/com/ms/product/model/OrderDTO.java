package com.ms.product.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    private int id;
    private long productId;
    private int ammount;
    private String subTotal;
    private String status;
}
