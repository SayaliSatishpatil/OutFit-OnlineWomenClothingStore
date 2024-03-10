package com.project.outfit.controller;

import com.project.outfit.dto.ProductEditDto;
import com.project.outfit.service.ProductServie;
import com.project.outfit.utils.response.GenericResponse;
import com.project.outfit.dto.ProductInputDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {
  private final ProductServie productServie;
  @PostMapping("/add")
  public ResponseEntity<GenericResponse> addProduct(@Valid @RequestBody final ProductInputDto productInputDto){
    log.info("Entry inside @class ProductController @method addProduct");
    return ResponseEntity.ok(productServie.createProduct(productInputDto));
  }
  @GetMapping("/products")
  public ResponseEntity<GenericResponse> getAllProducts(){
    log.info("Entry inside @class ProductController @method @fetchAllProducts");
    return ResponseEntity.ok(productServie.fetchProducts());
  }
  @GetMapping("/{productId}")
  public ResponseEntity<GenericResponse> getProduct(@PathVariable Integer productId){
    log.info("Entry inside @class ProductController @method getProduct");
    return ResponseEntity.ok(productServie.fetchProduct(productId));
  }
  @DeleteMapping("/{productId}")
  public ResponseEntity<GenericResponse> deleteProductById(@PathVariable Integer productId){
    log.info("Entry inside @class ProductController @method deleteProductById");

    return ResponseEntity.ok(productServie.deleteProduct(productId));
  }
  @PutMapping("/edit/{productId}")
  public ResponseEntity<GenericResponse> editProduct(@PathVariable Integer productId, @RequestBody ProductEditDto productEditDto){
    log.info("Entry inside @class ProductController @method editProduct");
    return ResponseEntity.ok().body(productServie.editProduct(productId, productEditDto));
  }

}
