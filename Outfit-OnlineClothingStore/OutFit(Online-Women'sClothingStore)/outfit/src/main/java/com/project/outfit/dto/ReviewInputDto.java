package com.project.outfit.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewInputDto {

  @NotNull(message = "Invalid product id, id shouldn't be null")
  private Integer productId;
  @NotBlank(message = "Invalid comment, this shouldn't be blank")
  private String comment;
  @NotNull(message = "Invalid rating, this shouldn't be null")
  @Min(value = 1, message = "Invalid value, rating shouldn't be less than 1")
  @Max(value = 5, message = "Invalid value, rating shouldn't be greater than 5")
  private Integer rating;

}
