package com.project.outfit.controller;

import com.project.outfit.service.CartService;
import com.project.outfit.utils.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class CartController {
  private final CartService cartService;

  @PostMapping("/add/{productId}")
  public ResponseEntity<GenericResponse> addInCart(@PathVariable Integer productId){
    log.info("Entry inside @class CartController @method addInCart");

    return ResponseEntity.ok(cartService.addProductInCart(productId));
  }
  @GetMapping("/items")
  public ResponseEntity<GenericResponse> getAllCartItems(){
    log.info("Entry inside @class CartController @method getAllCartItems");
    return ResponseEntity.ok(cartService.fetchAllCartItems());
  }
  @DeleteMapping("/{cartId}")
  public ResponseEntity<GenericResponse> deleteCartItem(@PathVariable Integer cartId){
    log.info("Entry inside @class CartController @method deleteCartItem");
    return ResponseEntity.ok(cartService.deleteItem(cartId));
  }

}
