package com.project.outfit.controller;

import com.project.outfit.service.OrderService;
import com.project.outfit.utils.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
  private final OrderService orderService;
  @PostMapping("/place/{cartId}/{address}")
  public ResponseEntity<GenericResponse> placeOrder(@PathVariable Integer cartId, @PathVariable String address){
    log.info("Entry inside @class OrderController @method placeOrder");

    return ResponseEntity.ok(orderService.createOrder(cartId, address));
  }
  @PutMapping("/cancel/{orderId}")
  public ResponseEntity<GenericResponse> cancelOrder(@PathVariable Integer orderId){
    log.info("Entry inside @class OrderController @method cancelOrder");

    return ResponseEntity.ok(orderService.cancelOrder(orderId));
  }
  @GetMapping("/user/orders")
  public ResponseEntity<GenericResponse> getAllUserOrders(){
    log.info("Entry inside @class OrderController @method getAllUserOrders");

    return ResponseEntity.ok(orderService.fetchAllUserOrders());
  }
  @PutMapping("/change/status/{statusId}/{orderId}")
  public ResponseEntity<GenericResponse> changeOrderStatus(@PathVariable Integer statusId, @PathVariable Integer orderId){
    log.info("Entry inside @class OrderController @method changeOrderStatus");

    return ResponseEntity.ok(orderService.changeOrderStatus(statusId, orderId));
  }
  @GetMapping("/orders")
  public ResponseEntity<GenericResponse> getAllOrders(){
    log.info("Entry inside @class OrderController @method getAllOrders");

    return ResponseEntity.ok(orderService.fetchAllOrders());
  }


}
