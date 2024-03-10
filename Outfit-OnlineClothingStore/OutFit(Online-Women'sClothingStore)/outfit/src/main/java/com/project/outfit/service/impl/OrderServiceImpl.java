package com.project.outfit.service.impl;

import com.project.outfit.dto.OrderDto;
import com.project.outfit.dto.OrderInputDto;
import com.project.outfit.entity.Cart;
import com.project.outfit.entity.Order;
import com.project.outfit.entity.Product;
import com.project.outfit.repository.CartRepository;
import com.project.outfit.repository.OrderRepository;
import com.project.outfit.repository.ProductRepository;
import com.project.outfit.service.OrderService;
import com.project.outfit.utils.constants.MessageConstants;
import com.project.outfit.utils.enums.ErrorEnums;
import com.project.outfit.utils.enums.OrderStatusEnums;
import com.project.outfit.utils.exception.OutfitException;
import com.project.outfit.utils.global.GlobalMethods;
import com.project.outfit.utils.global.GlobalValidation;
import com.project.outfit.utils.response.ErrorResponse;
import com.project.outfit.utils.response.GenericResponse;
import com.project.outfit.utils.response.OrderResponse;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private GlobalValidation globalValidation;
  @Autowired
  private GlobalMethods globalMethods;

  @Override
  public GenericResponse createOrder(final Integer cartId, final String address) {
    // Validate product id
    globalValidation.validateCartId(cartId);

    // Fetch current user id from token
    final Integer userId = globalMethods.fetchCurrentUserIdFromMail();

    final Optional<Cart> cart = cartRepository.findById(cartId);

    Optional<Product> product = null;
    if (cart.isPresent()){
      // Fetch product details by product id
       product = productRepository.findById(cart.get().getProductId());

    }
    
    if (Objects.requireNonNull(product).isPresent()) {
      final Order order = new Order();
      order.setAmount(product.get().getPrice());
      order.setDate(new Date());
      order.setAddress(address);
      order.setProductId(product.get().getId());
      order.setQuantity(cart.get().getQuantity());
      order.setStatus(OrderStatusEnums.PLACED.getStatus());
      order.setUserId(userId);

      // Save in a database
      orderRepository.save(order);
      
      // Delete from cart
      cartRepository.deleteById(cartId);
      
      return GenericResponse.success(MessageConstants.ORDER_PLACED_SUCCESS);
    }
    return null;
  }

  @Override
  public GenericResponse cancelOrder(final Integer orderId) {
    log.info("Entry inside @class OrderServiceImpl @method cancelOrder");

    // Validate order id
    validateOrderId(orderId);

    // Fetch order by id
    final Optional<Order> order = orderRepository.findById(orderId);
    System.out.println(order);

    if (order.isPresent()) {
      // Updated order status
      order.get().setStatus(OrderStatusEnums.CANCELLED.getStatus());

      // Updated in a database
      orderRepository.save(order.get());

      return GenericResponse.success(MessageConstants.CANCELLED_ORDER);
    }
    return null;
  }

  @Override
  public GenericResponse fetchAllUserOrders() {
    log.info("Entry inside @class OrderServiceImpl @method fetchAllUserOrders");

    // Fetch user id of from the token
    final Integer userId = globalMethods.fetchCurrentUserIdFromMail();

    // Fetch all orders of this user
    final List<Order> orders = orderRepository.findAllByUserId(userId);

    final List<Product> products = productRepository.findAll();

    final Set<OrderDto> orderDtos = new HashSet<>();

    orders.forEach(order -> {
      products.forEach(product -> {
        if (product.getId().equals(order.getProductId())){
          final OrderDto orderDto = new OrderDto();

          orderDto.setOrderId(order.getId());
          orderDto.setStatus(order.getStatus());
          orderDto.setQuantity(order.getQuantity());
          orderDto.setDate(order.getDate());
          orderDto.setAmount(order.getAmount());
          orderDto.setUserId(order.getUserId());
          orderDto.setAddress(order.getAddress());
          orderDto.setProduct(product);

          orderDtos.add(orderDto);
        }
      });
    });
    return GenericResponse.success(new OrderResponse(true, orderDtos));
  }

  @Override
  public GenericResponse changeOrderStatus(final Integer statusId, final Integer orderId) {
    // Validate order id
    validateOrderId(orderId);

    // Validate order status id
    validateOrderStatusId(statusId);

    // Fetch product by id
    final Optional<Order> order = orderRepository.findById(orderId);

    if (order.isPresent()){
      order.get().setStatus(statusId);
      orderRepository.save(order.get());
    }
    return GenericResponse.success(MessageConstants.STATUS_CHANGED_SUCCESS);
  }

  @Override
  public GenericResponse fetchAllOrders() {
    log.info("Entry inside @class OrderController @method fetchAllOrders");

    final List<Order> orders = orderRepository.findAll();
    final List<Product> products = productRepository.findAll();

    final Set<OrderDto> orderDtos = new HashSet<>();

    orders.forEach(order -> {
      products.forEach(product -> {
        if (product.getId().equals(order.getProductId())){
          final OrderDto orderDto = new OrderDto();

          orderDto.setOrderId(order.getId());
          orderDto.setStatus(order.getStatus());
          orderDto.setQuantity(order.getQuantity());
          orderDto.setDate(order.getDate());
          orderDto.setAmount(order.getAmount());
          orderDto.setUserId(order.getUserId());
          orderDto.setAddress(order.getAddress());
          orderDto.setProduct(product);

          orderDtos.add(orderDto);
        }
      });
    });
    return GenericResponse.success(new OrderResponse(true, orderDtos));
  }

  private void validateOrderStatusId(final Integer statusId) {
    if (statusId != OrderStatusEnums.CANCELLED.getStatus() &&
        statusId != OrderStatusEnums.DELIVERED.getStatus() &&
        statusId != OrderStatusEnums.PLACED.getStatus()) {

      throw new OutfitException(new ErrorResponse(
          false, ErrorEnums.INVALID_ORDER_STATUS_CODE.getMessage()
      ));
    }
  }


  private void validateOrderId(final Integer orderId) {
    if (!orderRepository.existsById(orderId)) {
      throw new OutfitException(new ErrorResponse(
          false, ErrorEnums.INVALID_ORDER_ID.getMessage()
      ));
    }
  }
}
