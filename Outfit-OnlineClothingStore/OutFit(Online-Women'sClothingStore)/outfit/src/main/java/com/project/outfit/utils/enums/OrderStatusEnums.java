package com.project.outfit.utils.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnums {
  PLACED(0),
  DELIVERED(1),
  CANCELLED(2);

  private final Integer status;

  OrderStatusEnums(Integer status) {
    this.status = status;
  }

}
