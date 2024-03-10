package com.project.outfit.utils.response;

import com.project.outfit.dto.OrderDto;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private Boolean status;
    private Set<OrderDto> orderDtos;

  public OrderResponse(final Boolean success, final Set<OrderDto> orderDtos) {
    this.status = success;
    this.orderDtos = orderDtos;
  }
}
