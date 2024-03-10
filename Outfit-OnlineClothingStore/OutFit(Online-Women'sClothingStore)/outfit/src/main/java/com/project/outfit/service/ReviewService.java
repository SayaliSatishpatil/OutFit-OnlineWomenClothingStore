package com.project.outfit.service;

import com.project.outfit.dto.ReviewInputDto;
import com.project.outfit.utils.response.GenericResponse;

public interface ReviewService {

  GenericResponse createReview(ReviewInputDto reviewInputDto);

  GenericResponse fetchAllReviews(Integer productId);
}
