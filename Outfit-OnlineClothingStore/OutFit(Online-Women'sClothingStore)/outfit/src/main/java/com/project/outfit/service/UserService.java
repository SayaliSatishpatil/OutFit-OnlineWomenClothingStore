package com.project.outfit.service;


import com.project.outfit.dto.UserInputDto;
import com.project.outfit.utils.response.GenericResponse;
import com.project.outfit.utils.wrapper.LoginWrapper;
import com.project.outfit.utils.wrapper.UpdatePasswordWrapper;

public interface UserService {

    GenericResponse logInAndGenerateToken(LoginWrapper loginWrapper);

    GenericResponse addUser(UserInputDto userInputDto);

    GenericResponse fetchUserDetailsById();

    GenericResponse updatePassword(UpdatePasswordWrapper updatePasswordWrapper);
}
