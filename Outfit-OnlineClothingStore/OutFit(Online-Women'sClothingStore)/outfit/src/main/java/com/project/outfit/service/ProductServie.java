package com.project.outfit.service;

import com.project.outfit.dto.ProductEditDto;
import com.project.outfit.dto.ProductInputDto;
import com.project.outfit.utils.response.GenericResponse;

public interface ProductServie {

   GenericResponse createProduct(ProductInputDto productInputDto);

  GenericResponse fetchProducts();

  GenericResponse fetchProduct(Integer productId);

  GenericResponse deleteProduct(Integer productId);

    GenericResponse editProduct(Integer productId, ProductEditDto productEditDto);
}
