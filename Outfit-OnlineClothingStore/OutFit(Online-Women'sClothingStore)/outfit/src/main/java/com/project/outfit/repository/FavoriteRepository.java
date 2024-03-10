package com.project.outfit.repository;

import com.project.outfit.entity.Favorites;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, Integer> {

  List<Favorites> findAllByUserId(Integer userId);

  void deleteByProductId(Integer productId);
}
