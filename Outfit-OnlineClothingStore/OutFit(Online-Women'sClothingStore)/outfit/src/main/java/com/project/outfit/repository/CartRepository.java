package com.project.outfit.repository;

import com.project.outfit.entity.Cart;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

  List<Cart> findAllByUserId(Integer userId);

  void deleteByProductId(Integer productId);

  @Query(value = "select * from cart where product_id=?1 and user_id=?2",nativeQuery = true)
  Optional<Cart> findExistingCartProduct(Integer productId, Integer userId);
}
