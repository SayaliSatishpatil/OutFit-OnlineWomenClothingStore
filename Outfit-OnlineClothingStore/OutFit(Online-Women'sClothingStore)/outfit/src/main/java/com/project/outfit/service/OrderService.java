package com.project.outfit.service;

import com.project.outfit.utils.response.GenericResponse;

public interface OrderService {

  GenericResponse createOrder(Integer cartId, String address);

  GenericResponse cancelOrder(Integer orderId);

  GenericResponse fetchAllUserOrders();

  GenericResponse changeOrderStatus(Integer statusId, Integer orderId);

  GenericResponse fetchAllOrders();
}
