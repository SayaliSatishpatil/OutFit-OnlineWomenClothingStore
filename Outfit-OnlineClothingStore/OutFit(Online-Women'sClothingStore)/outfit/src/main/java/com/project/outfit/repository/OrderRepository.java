package com.project.outfit.repository;

import com.project.outfit.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findAllByUserId(Integer userId);
}
