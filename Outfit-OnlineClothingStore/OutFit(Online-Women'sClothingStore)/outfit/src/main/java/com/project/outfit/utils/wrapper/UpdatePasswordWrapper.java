package com.project.outfit.utils.wrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordWrapper {
    private String oldPassword;
    private String newPassword;
}
