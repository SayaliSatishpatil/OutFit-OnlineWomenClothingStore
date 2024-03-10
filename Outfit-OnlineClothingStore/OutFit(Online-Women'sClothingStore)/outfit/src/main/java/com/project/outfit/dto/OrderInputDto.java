package com.project.outfit.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderInputDto {
  @NotNull(message = "Provide valid product, id shouldn't be null")
  private Integer productId;
  @NotNull(message = "Provide valid quantity, quantity shouldn't be null")
  @Min(value = 1, message = "Quantity shouldn't be less than 1")
  private Integer quantity;
  @NotBlank(message = "Provide valid address, address shouldn't be empty")
  private String address;

}
