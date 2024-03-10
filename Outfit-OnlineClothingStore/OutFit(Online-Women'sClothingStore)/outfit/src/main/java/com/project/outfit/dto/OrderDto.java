package com.project.outfit.dto;

import com.project.outfit.entity.Product;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
  private Integer orderId;
  private Integer userId;
  private Integer quantity;
  private Double amount;
  private Integer status;
  private String address;
  private Date date;
  private Product product;
}
