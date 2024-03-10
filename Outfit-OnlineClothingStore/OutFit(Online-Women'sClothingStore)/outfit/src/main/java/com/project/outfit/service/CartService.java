package com.project.outfit.service;

import com.project.outfit.utils.response.GenericResponse;

public interface CartService {

   GenericResponse addProductInCart(Integer productId);

   GenericResponse fetchAllCartItems();

  GenericResponse deleteItem(Integer productId);
}
