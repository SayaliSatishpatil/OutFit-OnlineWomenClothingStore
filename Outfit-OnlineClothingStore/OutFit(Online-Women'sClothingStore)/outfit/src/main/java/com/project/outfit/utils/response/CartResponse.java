package com.project.outfit.utils.response;

import com.project.outfit.entity.Cart;
import com.project.outfit.entity.Product;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartResponse {
  private Integer id;
  private Integer userId;
  private Integer quantity;
  private Date date;
  private Product product;

}
