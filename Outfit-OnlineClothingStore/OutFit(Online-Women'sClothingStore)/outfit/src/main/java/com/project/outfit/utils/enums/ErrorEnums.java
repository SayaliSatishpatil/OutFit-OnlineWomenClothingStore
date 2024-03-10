package com.project.outfit.utils.enums;

import lombok.Getter;

@Getter
public enum ErrorEnums {
    INVALID_EMAIL_FORMAT("Invalid email format, provide valid email"),
    EMAIL_ALREADY_EXISTS("Invalid email, email already exist provide new email to sign up"),
    INVALID_EMAIL("Invalid email, provided email is not exists"),
    INVALID_PASSWORD_TO_LOGIN("Invalid password, provide valid password to login"),
    INVALID_USER_ID("Invalid user id, provide valid user id"),
    INVALID_CATEGORY_ID("Invalid category id, provide valid id"),
    INVALID_PRODUCT_ID("Invalid product id, provide valid id"),
    UNABLE_FETCH_USER_ID("Unable to fetch user id, unexpected error occurred"),
    INVALID_ORDER_ID("Invalid order id, provide valid order id"),
    INVALID_CART_ID("Invalid cart id, provide valid id"),
    INVALID_ORDER_STATUS_CODE("Invalid order status code, provide valid code"),
    INVALID_OLD_PASSWORD("Invalid old password, provide valid password to update");


    private final String message;
    ErrorEnums(String message){
        this.message = message;
    }
}
