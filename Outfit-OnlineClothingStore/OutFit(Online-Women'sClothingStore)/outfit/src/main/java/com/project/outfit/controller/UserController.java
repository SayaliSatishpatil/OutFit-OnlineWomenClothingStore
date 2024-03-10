package com.project.outfit.controller;


import com.project.outfit.dto.UserInputDto;
import com.project.outfit.service.UserService;
import com.project.outfit.utils.response.GenericResponse;
import com.project.outfit.utils.wrapper.LoginWrapper;
import javax.validation.Valid;

import com.project.outfit.utils.wrapper.UpdatePasswordWrapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/token/login")
  public ResponseEntity<GenericResponse> generateTokenLogin(
      @RequestBody final LoginWrapper loginWrapper) {
    return ResponseEntity.ok().body(userService.logInAndGenerateToken(loginWrapper));

  }

  @PostMapping("/create")
  public ResponseEntity<GenericResponse> createUser(@Valid @RequestBody UserInputDto userInputDto) {
    try {
      GenericResponse response = userService.addUser(userInputDto);
      return ResponseEntity.ok(response);
    } catch (Exception exception) {
      log.error("Error occurred while creating user", exception);
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(GenericResponse.error("Internal Server Error"));
    }
  }

  @GetMapping("/")
  public ResponseEntity<GenericResponse> getUserInfo() {
    log.info("Retrieving user details for email:");

    return ResponseEntity.ok().body(userService.fetchUserDetailsById());
  }
  @PutMapping("/update/password")
  public ResponseEntity<GenericResponse> updateUserPassword(@RequestBody UpdatePasswordWrapper updatePasswordWrapper){
    log.info("Entry inside @class UserController @method updateUserPassword");

    return ResponseEntity.ok().body(userService.updatePassword(updatePasswordWrapper));
  }

}
