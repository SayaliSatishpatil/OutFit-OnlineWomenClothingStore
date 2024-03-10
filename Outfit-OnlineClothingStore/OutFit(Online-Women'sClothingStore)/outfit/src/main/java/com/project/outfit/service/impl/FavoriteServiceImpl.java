package com.project.outfit.service.impl;

import com.project.outfit.entity.Favorites;
import com.project.outfit.repository.FavoriteRepository;
import com.project.outfit.service.FavoriteService;
import com.project.outfit.utils.constants.MessageConstants;
import com.project.outfit.utils.global.GlobalMethods;
import com.project.outfit.utils.global.GlobalValidation;
import com.project.outfit.utils.response.GenericResponse;
import java.util.Date;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class FavoriteServiceImpl implements FavoriteService {
  @Autowired
  private GlobalValidation globalValidation;
  @Autowired
  private GlobalMethods globalMethods;
  @Autowired
  private FavoriteRepository favoriteRepository;

  @Override
  public GenericResponse addFavoriteProduct(final Integer productId) {
    // Validate product id
    globalValidation.validateProductId(productId);

    // Fetch current user id from token
    final Integer userId = globalMethods.fetchCurrentUserIdFromMail();

    // Set favorites items
    final Favorites favorites = new Favorites();
    favorites.setProductId(productId);
    favorites.setUserId(userId);
    favorites.setDate(new Date());

    // Save in a database
    favoriteRepository.save(favorites);

    return GenericResponse.success(MessageConstants.ITEM_ADDED_IN_FAVORITES);
  }

  @Override
  public GenericResponse fetchAllFavoritesItems() {
    log.info("Entry inside @class FavoriteServiceImpl @method fetchAllFavoritesItems");

    // Fetch user id from token
    final Integer userId = globalMethods.fetchCurrentUserIdFromMail();

    // Fetch favorites items by user id
    final List<Favorites> favorites = favoriteRepository.findAllByUserId(userId);

    return GenericResponse.success(favorites);
  }

  @Override
  public GenericResponse deleteProductById(final Integer productId) {
    // Validate product id
    globalValidation.validateProductId(productId);

    favoriteRepository.deleteByProductId(productId);
    return GenericResponse.success(MessageConstants.REMOVED_ITEM_FROM_FAVORITES);
  }
}
