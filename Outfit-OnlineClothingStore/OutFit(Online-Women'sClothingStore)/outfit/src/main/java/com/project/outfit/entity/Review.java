package com.project.outfit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "product_id")
  private Integer productId;
  @Column(name = "user_id")
  private Integer userId;
  @Column(name = "created_at")
  private Date date;
  @Column(name = "comment")
  private String comment;;
  @Column(name = "rating")
  private Integer rating;

}
