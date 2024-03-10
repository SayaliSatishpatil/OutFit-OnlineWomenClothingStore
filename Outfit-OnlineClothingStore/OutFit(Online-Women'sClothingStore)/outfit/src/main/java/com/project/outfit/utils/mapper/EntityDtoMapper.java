package com.project.outfit.utils.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.outfit.dto.ProductInputDto;
import com.project.outfit.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {
  @Autowired
  private ObjectMapper objectMapper;

  public Product productToDto(final ProductInputDto productInputDto){
    return objectMapper.convertValue(productInputDto, Product.class);
  }

}
