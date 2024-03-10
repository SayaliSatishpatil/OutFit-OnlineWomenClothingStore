package com.project.outfit.utils.global;


import com.project.outfit.repository.CartRepository;
import com.project.outfit.repository.CategoryRepository;
import com.project.outfit.repository.OrderRepository;
import com.project.outfit.repository.ProductRepository;
import com.project.outfit.repository.UserRepository;
import com.project.outfit.utils.enums.ErrorEnums;
import com.project.outfit.utils.exception.OutfitException;
import com.project.outfit.utils.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GlobalValidation {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public void validateEmail(final String email) {
        if (Boolean.FALSE.equals(userRepository.existsByEmail(email))) {
            throw new OutfitException(new ErrorResponse(
                    false, ErrorEnums.INVALID_EMAIL.getMessage()
            ));
        }
    }

    public void validateEmailFormat(final String email) {
        if (!email.contains("@") || !email.contains(".com")) {
            throw new OutfitException(
                    new ErrorResponse(false, ErrorEnums.INVALID_EMAIL_FORMAT.getMessage()));
        }
    }



    public void validateCategoryId(final Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new OutfitException(new ErrorResponse(
                    false, ErrorEnums.INVALID_CATEGORY_ID.getMessage()
            ));
        }
    }

    public void validateProductId(final Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new OutfitException(new ErrorResponse(
                    false, ErrorEnums.INVALID_PRODUCT_ID.getMessage()
            ));
        }
    }

    public void validateCartId(final Integer cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new OutfitException(new ErrorResponse(
                    false, ErrorEnums.INVALID_CART_ID.getMessage()
            ));
        }
    }

    public void validateOrderId(final Integer orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OutfitException(new ErrorResponse(
                    false, ErrorEnums.INVALID_ORDER_ID.getMessage()
            ));
        }
    }
}
