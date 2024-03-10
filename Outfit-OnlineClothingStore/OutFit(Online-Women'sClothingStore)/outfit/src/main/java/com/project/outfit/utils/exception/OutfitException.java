package com.project.outfit.utils.exception;


import com.project.outfit.utils.response.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutfitException extends RuntimeException{
    private ErrorResponse errorResponse;

   public OutfitException(ErrorResponse errorResponse){
       this.errorResponse = errorResponse;
   }
}
