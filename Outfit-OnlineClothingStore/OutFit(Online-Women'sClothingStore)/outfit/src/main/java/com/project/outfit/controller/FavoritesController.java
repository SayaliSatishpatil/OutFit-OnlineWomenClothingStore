package com.project.outfit.controller;

import com.project.outfit.entity.Favorites;
import com.project.outfit.service.FavoriteService;
import com.project.outfit.utils.response.GenericResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoritesController {
  private final FavoriteService favoriteService;
  @PostMapping("/{productId}")
  public ResponseEntity<GenericResponse> createFavoriteProduct(@PathVariable Integer productId){
    log.info("Entry inside @class FavoritesController @method createFavoriteProduct");
    return ResponseEntity.ok(favoriteService.addFavoriteProduct(productId));

  }
  @GetMapping("/favorites")
  public ResponseEntity<GenericResponse> getAllFavorites(){
    log.info("Entry inside @class FavoritesController @method getAllFavorites");
    return ResponseEntity.ok(favoriteService.fetchAllFavoritesItems());
  }
  @DeleteMapping("/{productId}")
  public ResponseEntity<GenericResponse> deleteProductById(@PathVariable Integer productId){
    log.info("Entry inside @class FavoritesController @method deleteProductById");
    return ResponseEntity.ok(favoriteService.deleteProductById(productId));
  }

}
