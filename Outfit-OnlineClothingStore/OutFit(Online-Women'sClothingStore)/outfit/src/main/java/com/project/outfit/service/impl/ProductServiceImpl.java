package com.project.outfit.service.impl;

import com.project.outfit.dto.ProductEditDto;
import com.project.outfit.dto.ProductInputDto;
import com.project.outfit.entity.Product;
import com.project.outfit.repository.ProductRepository;
import com.project.outfit.service.ProductServie;
import com.project.outfit.utils.constants.MessageConstants;
import com.project.outfit.utils.constants.ProductStatusConstants;
import com.project.outfit.utils.mapper.EntityDtoMapper;
import com.project.outfit.utils.response.GenericResponse;
import com.project.outfit.utils.global.GlobalValidation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProductServiceImpl implements ProductServie {

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private GlobalValidation globalValidation;
  @Autowired
  private EntityDtoMapper entityDtoMapper;

  @Override
  public GenericResponse createProduct(final ProductInputDto productInputDto) {
    log.info("Entry inside @class ProductServiceImpl @method @createProduct");

    // Validate category id
    globalValidation.validateCategoryId(productInputDto.getCategoryId());

    final Product product = entityDtoMapper.productToDto(productInputDto);

    product.setStatus(ProductStatusConstants.ACTIVE);
    product.setDate(new Date());

    // Save in a database
    productRepository.save(product);

    return GenericResponse.success(MessageConstants.PRODUCT_CREATED_SUCCESSFULLY);
  }

  @Override
  public GenericResponse fetchProducts() {
    log.info("Entry inside @class ProductServiceImpl @method fetchProducts");

    final List<Product> productList = productRepository.findAll();
    return GenericResponse.success(productList);
  }

  @Override
  public GenericResponse fetchProduct(final Integer productId) {
    log.info("Entry inside @class ProductServiceImpl @method fetchProduct");
    // Validate product id
    globalValidation.validateProductId(productId);

    final Optional<Product> product = productRepository.findById(productId);

    if (product.isPresent()){
      return GenericResponse.success(product);
    }
    return null;
  }

  @Override
  public GenericResponse deleteProduct(final Integer productId) {
    // Validate product id
    globalValidation.validateProductId(productId);

    // delete product by id
    productRepository.deleteById(productId);

    return GenericResponse.success(MessageConstants.DELETE_PRODUCT_SUCCESS);
  }

  @Override
  public GenericResponse editProduct(final Integer productId, final ProductEditDto productEditDto) {
    // Validate product id
    globalValidation.validateProductId(productId);

    // Fetch product by id
    final Optional<Product> product = productRepository.findById(productId);

    if (product.isPresent()){
      product.get().setName(productEditDto.getName());
      product.get().setPrice(productEditDto.getPrice());
      product.get().setDescription(productEditDto.getDescription());
      product.get().setImageUrl(productEditDto.getImageUrl());

      productRepository.save(product.get());
    }
     return GenericResponse.success(MessageConstants.PRODUCT_UPDATED_SUCCESS);
  }
}
