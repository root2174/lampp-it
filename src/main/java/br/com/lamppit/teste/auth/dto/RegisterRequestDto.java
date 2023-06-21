package br.com.lamppit.teste.auth.dto;

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
    @ApiModelProperty(example = "admin@admin.com")
    private String email;
}
