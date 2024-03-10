package com.project.outfit.utils.global;

import com.project.outfit.entity.User;
import com.project.outfit.repository.UserRepository;
import com.project.outfit.utils.enums.ErrorEnums;
import com.project.outfit.utils.exception.OutfitException;
import com.project.outfit.utils.response.ErrorResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GlobalMethods {
  @Autowired
  private UserRepository userRepository;
  public Integer fetchCurrentUserIdFromMail() {
    // Get current user email from token
    final String currentUserMail = SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal().toString();

    // Fetch user id from mail
    final Optional<User> user = userRepository.findByEmail(currentUserMail);

    if (user.isPresent()) {
      return user.get().getId();
    } else {
      throw new OutfitException(new ErrorResponse(
          false, ErrorEnums.UNABLE_FETCH_USER_ID.getMessage()
      ));
    }
  }

}
