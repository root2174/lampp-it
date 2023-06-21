package br.com.lamppit.teste.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginRequestDto {
    @Email
    @NotBlank
    @ApiModelProperty(example = "admin@admin.com")
    private String email;

    @NotBlank
    @ApiModelProperty(example = "123456")
    private String password;
}