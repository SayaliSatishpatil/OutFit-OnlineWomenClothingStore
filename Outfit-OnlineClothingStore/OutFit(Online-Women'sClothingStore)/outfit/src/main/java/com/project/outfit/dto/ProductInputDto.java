package com.project.outfit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@Component
public class ProductInputDto {

  @NotBlank(message = "Invalid product name, name shouldn't be empty")
  private String name;
  @NotBlank(message = "Invalid brand name, name shouldn't be empty")
  private String brand;
  @NotBlank(message = "Invalid product description, name shouldn't be empty")
  private String description;
  @NotNull(message = "Invalid product name, name shouldn't be empty")
  @Min(value = 1, message = "Invalid price, price shouldn't be less than 1")
  private Double price;
  @NotBlank(message = "Invalid image url, url shouldn't be empty")
  private String imageUrl;
  @NotNull(message = "Invalid category, id shouldn't be null")
  @Min(value = 1, message = "Invalid category id, category shouldn't be null")
  private Integer categoryId;

}
