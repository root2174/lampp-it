package br.com.lamppit.teste.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {

    @ApiModelProperty(example = "company@company.com")
    private String email;

    @ApiModelProperty(example = "123456")
    private String password;
}
