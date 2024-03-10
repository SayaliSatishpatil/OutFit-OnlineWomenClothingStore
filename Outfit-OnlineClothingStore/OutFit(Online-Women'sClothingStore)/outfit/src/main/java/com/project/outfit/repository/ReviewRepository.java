package com.project.outfit.repository;

import com.project.outfit.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

  List<Review> findAllByProductId(Integer productId);
}
