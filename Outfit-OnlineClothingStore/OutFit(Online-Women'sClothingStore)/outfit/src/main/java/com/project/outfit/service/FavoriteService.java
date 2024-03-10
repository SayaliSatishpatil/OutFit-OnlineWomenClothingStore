package com.project.outfit.service;

import com.project.outfit.utils.response.GenericResponse;

public interface FavoriteService {

  GenericResponse addFavoriteProduct(Integer productId);

  GenericResponse fetchAllFavoritesItems();

  GenericResponse deleteProductById(Integer productId);
}
