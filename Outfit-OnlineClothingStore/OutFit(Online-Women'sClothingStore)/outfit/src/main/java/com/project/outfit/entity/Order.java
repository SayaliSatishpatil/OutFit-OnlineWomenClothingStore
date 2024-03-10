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
@Entity(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "user_id")
  private Integer userId;
  @Column(name = "product_id")
  private Integer productId;
  @Column(name = "quantity")
  private Integer quantity;
  @Column(name = "amount")
  private Double amount;
  @Column(name = "status")
  private Integer status;
  @Column(name = "address")
  private String address;
  @Column(name = "created_at")
  private Date date;

}
