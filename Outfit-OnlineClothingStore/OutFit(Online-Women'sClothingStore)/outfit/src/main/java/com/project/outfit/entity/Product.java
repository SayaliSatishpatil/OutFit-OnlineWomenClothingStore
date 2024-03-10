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
@Entity(name = "products")
@NoArgsConstructor
public class Product {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "name")
  private String name;
  @Column(name = "brand")
  private String brand;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private Double price;
  @Column(name = "image_url")
  private String imageUrl;
  @Column(name = "category_id")
  private Integer categoryId;
  @Column(name = "created_at")
  private Date date;
  @Column(name = "status")
  private String status;


}
