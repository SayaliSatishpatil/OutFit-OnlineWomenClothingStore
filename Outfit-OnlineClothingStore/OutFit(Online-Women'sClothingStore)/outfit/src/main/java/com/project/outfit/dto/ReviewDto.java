package com.project.outfit.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewDto {
    private String comment;
    private Integer rating;
    private Date createdAt;
    private String userName;
}
