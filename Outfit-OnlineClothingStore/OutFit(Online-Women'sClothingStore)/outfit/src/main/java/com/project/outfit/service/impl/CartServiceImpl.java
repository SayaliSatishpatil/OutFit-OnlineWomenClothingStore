package com.project.outfit.service.impl;

import com.project.outfit.entity.Cart;
import com.project.outfit.entity.Product;
import com.project.outfit.repository.CartRepository;
import com.project.outfit.repository.ProductRepository;
import com.project.outfit.repository.UserRepository;
import com.project.outfit.service.CartService;
import com.project.outfit.utils.constants.MessageConstants;
import com.project.outfit.utils.global.GlobalMethods;
import com.project.outfit.utils.global.GlobalValidation;
import com.project.outfit.utils.response.CartResponse;
import com.project.outfit.utils.response.GenericResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CartServiceImpl implements CartService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private GlobalValidation globalValidation;
  @Autowired
  private GlobalMethods globalMethods;

  @Override
  public GenericResponse addProductInCart(final Integer productId) {
    log.info("Entry inside @method CartServiceImpl @method addProductInCart");

    // Validate product id
    globalValidation.validateProductId(productId);

    // Fetch user id from mail
    final Integer userId = globalMethods.fetchCurrentUserIdFromMail();

    // Fetch product by cartId product id and cart id
    Optional<Cart> existingCartProduct = cartRepository.findExistingCartProduct(productId, userId);

    if (existingCartProduct.isPresent()) {
      final Integer quantity = existingCartProduct.get().getQuantity() + 1;
      existingCartProduct.get().setQuantity(quantity);
      cartRepository.save(existingCartProduct.get());

      return GenericResponse.success(MessageConstants.INCREASED_CART_QUANTITY);
    }

    // Save cart details
    final Cart cart = new Cart();
    cart.setProductId(productId);
    cart.setUserId(userId);
    cart.setDate(new Date());
    // At the very first step quantity is 1, update if user updates
    cart.setQuantity(1);

    // Save in a database
    cartRepository.save(cart);

    return GenericResponse.success(MessageConstants.ITEM_ADDED_IN_CART);
  }


  @Override
  public GenericResponse fetchAllCartItems() {
    log.info("Entry inside @class CartServiceImpl @method fetchAllCartItems");

    // Fetch user id from mail
    final Integer userId = globalMethods.fetchCurrentUserIdFromMail();

    final List<Cart> cartList = cartRepository.findAllByUserId(userId);
    final List<Product> products = productRepository.findAll();


    final Set<CartResponse> cartResponses = new HashSet<>();

    cartList.forEach(cart -> {
      products.forEach(product -> {

        if (cart.getProductId().equals(product.getId())) {
          final CartResponse cartResponse = new CartResponse();

          cartResponse.setId(cart.getId());
          cartResponse.setQuantity(cart.getQuantity());
          cartResponse.setDate(cart.getDate());
          cartResponse.setUserId(cart.getUserId());
          cartResponse.setProduct(product);

          cartResponses.add(cartResponse);
        }
      });
    });

    return GenericResponse.success(cartResponses);
  }

  @Override
  public GenericResponse deleteItem(Integer cartId) {
    // Validate product id
    globalValidation.validateCartId(cartId);

    // Fetch product by cartId
    Optional<Cart> cart = cartRepository.findById(cartId);

    if (cart.isPresent()) {
      if (cart.get().getQuantity() == 1) {
        cartRepository.deleteById(cartId);

        return GenericResponse.success(MessageConstants.CART_ITEM_REMOVED);
      } else if (cart.get().getQuantity() > 1) {

        final Integer quantity = cart.get().getQuantity() - 1;
        cart.get().setQuantity(quantity);
        cartRepository.save(cart.get());

        return GenericResponse.success(MessageConstants.DECREASED_CART_QUANTITY);
      }
    }
    return GenericResponse.success(null);
  }
}
