package com.project.outfit.service.impl;

import com.project.outfit.dto.ReviewDto;
import com.project.outfit.dto.ReviewInputDto;
import com.project.outfit.entity.Review;
import com.project.outfit.entity.User;
import com.project.outfit.repository.ReviewRepository;
import com.project.outfit.repository.UserRepository;
import com.project.outfit.service.ReviewService;
import com.project.outfit.utils.constants.MessageConstants;
import com.project.outfit.utils.global.GlobalMethods;
import com.project.outfit.utils.global.GlobalValidation;
import com.project.outfit.utils.response.GenericResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;
  @Autowired
  private GlobalMethods globalMethods;
  @Autowired
  private GlobalValidation globalValidation;
  @Autowired
  private UserRepository userRepository;

  @Override
  public GenericResponse createReview(final ReviewInputDto reviewInputDto) {
    log.info("Entry inside @class ReviewServiceImpl @method createReview");

    // Validate product id
    globalValidation.validateProductId(reviewInputDto.getProductId());

    // Fetch user id from token
    final Integer userId = globalMethods.fetchCurrentUserIdFromMail();

    final Review review = new Review();
    review.setUserId(userId);
    review.setProductId(reviewInputDto.getProductId());
    review.setDate(new Date());
    review.setRating(reviewInputDto.getRating());
    review.setComment(reviewInputDto.getComment());

    // Save in a database
    reviewRepository.save(review);

    return GenericResponse.success(MessageConstants.REVIEW_ADDED_SUCCESS);
  }

  @Override
  public GenericResponse fetchAllReviews(final Integer productId) {
    // Validate product id
    globalValidation.validateProductId(productId);

    // Fetch all review by product id
    final List<Review> reviews = reviewRepository.findAllByProductId(productId);
    final List<User> users = userRepository.findAll();

    final List<ReviewDto> reviewDtos = new ArrayList<>();

    reviews.forEach(review -> {
      users.forEach(user -> {
        if (user.getId().equals(review.getUserId())){
          final ReviewDto reviewDto = new ReviewDto();

          reviewDto.setUserName(user.getUsername());
          reviewDto.setRating(review.getRating());
          reviewDto.setComment(review.getComment());
          reviewDto.setCreatedAt(review.getDate());

          reviewDtos.add(reviewDto);
        }
      });
    });

    return GenericResponse.success(reviewDtos);
  }
}
