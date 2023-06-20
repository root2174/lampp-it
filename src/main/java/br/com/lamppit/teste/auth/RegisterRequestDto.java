package br.com.lamppit.teste.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotBlank
    @ApiModelProperty(example = "123456")
    private String password;

    @NotBlank
    private String email;
}
