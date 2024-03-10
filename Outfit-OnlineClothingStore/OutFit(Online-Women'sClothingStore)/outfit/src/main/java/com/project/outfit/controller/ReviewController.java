package com.project.outfit.controller;

import com.project.outfit.dto.ReviewInputDto;
import com.project.outfit.service.ReviewService;
import com.project.outfit.utils.response.GenericResponse;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@Log4j2
public class ReviewController {
  @Autowired
  private ReviewService reviewService;
  @PostMapping("/add")
  public ResponseEntity<GenericResponse> sendReview(@Valid @RequestBody ReviewInputDto reviewInputDto){
    log.info("Entry inside @class ReviewController @method sendReview");
    return ResponseEntity.ok(reviewService.createReview(reviewInputDto));
  }
  @GetMapping("/reviews/{productId}")
  public ResponseEntity<GenericResponse> getAllReviews(@PathVariable Integer productId){
    log.info("Entry inside @class ReviewController @method getAllReviews");
    return ResponseEntity.ok(reviewService.fetchAllReviews(productId));
  }
}
