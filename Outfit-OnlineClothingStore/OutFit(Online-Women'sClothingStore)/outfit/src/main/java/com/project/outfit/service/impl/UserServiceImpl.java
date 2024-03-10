package com.project.outfit.service.impl;

import com.project.outfit.dto.UserInputDto;
import com.project.outfit.entity.User;
import com.project.outfit.repository.UserRepository;
import com.project.outfit.security.JwtService;
import com.project.outfit.service.UserService;
import com.project.outfit.utils.constants.MessageConstants;
import com.project.outfit.utils.constants.RoleConstants;
import com.project.outfit.utils.enums.ErrorEnums;
import com.project.outfit.utils.exception.OutfitException;
import com.project.outfit.utils.global.GlobalMethods;
import com.project.outfit.utils.global.GlobalValidation;
import com.project.outfit.utils.response.ErrorResponse;
import com.project.outfit.utils.response.GenericResponse;
import com.project.outfit.utils.wrapper.LoginWrapper;
import com.project.outfit.utils.wrapper.TokenWrapper;

import java.util.Date;
import java.util.Optional;

import com.project.outfit.utils.wrapper.UpdatePasswordWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GlobalValidation globalValidation;
    private final GlobalMethods globalMethods;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public GenericResponse logInAndGenerateToken(final LoginWrapper loginWrapper) {
        log.info("Entry inside @class UserServiceImpl @method logInAndGenerateToken");

        // Validate login credentials
        validateLoginCredentails(loginWrapper.getEmail(), loginWrapper.getPassword());

        final String token = JwtService.generateJwtToken(loginWrapper.getEmail());

        final TokenWrapper tokenWrapper = new TokenWrapper();
        tokenWrapper.setAccessToken(token);

        final Optional<User> user = userRepository.findByEmail(loginWrapper.getEmail());
        String role = null;
        if (user.isPresent()) {
            role = user.get().getRole();
        }
        tokenWrapper.setRole(role);
        return GenericResponse.success(tokenWrapper);
    }

    @Override
    public GenericResponse addUser(final UserInputDto userInputDto) {
        log.info("Entry inside @class UserServiceImpl @method addUser");

        // Validate email format
        globalValidation.validateEmailFormat(userInputDto.getEmail());

        // Validate email already exists or not
        validateEmailExistence(userInputDto.getEmail());

        // Encode password
        final String password = bCryptPasswordEncoder.encode(userInputDto.getPassword());

        // Store user info in database
        userRepository.save(convertUserDtoToUser(userInputDto, password));

        log.info("User saved in database");
        return GenericResponse.success("User Created Successfully");
    }

    @Override
    public GenericResponse fetchUserDetailsById() {
        // Validate email format
        final String currentUserMail = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()
                .toString();

        // Fetch user by id
        final Optional<User> user = userRepository.findByEmail(currentUserMail);

        return GenericResponse.success(user);
    }

    @Override
    public GenericResponse updatePassword(final UpdatePasswordWrapper updatePasswordWrapper) {
        log.info("Entry inside @class userServiceImpl @method updatePassword");

        // Get current user id from token
        final Integer userId = globalMethods.fetchCurrentUserIdFromMail();

        // Fetch user
        final Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            // Validate old password
            validateOldPassword(user.get().getPassword(), updatePasswordWrapper.getOldPassword());
            // Set new password
            final String password = bCryptPasswordEncoder.encode(updatePasswordWrapper.getNewPassword());
            user.get().setPassword(password);


            userRepository.save(user.get());
        }
        return GenericResponse.success(MessageConstants.PASSWORD_UPDATED_SUCCESS);
    }

    private void validateOldPassword(String existingOldPassword, String providedOldPassword) {
        if (!(bCryptPasswordEncoder.matches(providedOldPassword, existingOldPassword))) {
            throw new OutfitException(new ErrorResponse(false, ErrorEnums.INVALID_OLD_PASSWORD.getMessage()));
        }
    }


    private User convertUserDtoToUser(final UserInputDto userInputDto, final String password) {
        log.info("Entry inside @class UserServiceImpl @method convertUserDtoToUser");
        final User user = new User();
        user.setUsername(userInputDto.getUsername());
        user.setEmail(userInputDto.getEmail());
        user.setAge(userInputDto.getAge());
        user.setPassword(password);
        user.setRole(RoleConstants.USER);
        user.setCreatedAt(new Date());

        return user;
    }

    private void validateEmailExistence(final String email) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(email))) {
            throw new OutfitException(
                    new ErrorResponse(false, ErrorEnums.EMAIL_ALREADY_EXISTS.getMessage())
            );
        }
    }

    private void validateLoginCredentails(final String email, final String password) {
        // Validate Email is valid or not
        globalValidation.validateEmail(email);

        final Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && !bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            throw new OutfitException(new ErrorResponse(
                    false, ErrorEnums.INVALID_PASSWORD_TO_LOGIN.getMessage()
            ));
        }
    }
}
