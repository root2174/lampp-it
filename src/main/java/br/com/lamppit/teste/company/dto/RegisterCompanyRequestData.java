package br.com.lamppit.teste.company.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCompanyRequestData {

    @Email
    @NotBlank
    @ApiModelProperty(example = "company@company.com")
    private String email;

    @ApiModelProperty(example = "123456")
    @NotBlank
    private String password;

    @Pattern(regexp = "^\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}-?\\d{2}$")
    @ApiModelProperty(example = "20.553.518/0001-60")
    @NotBlank
    private String cnpj;

    @ApiModelProperty(example = "1234")
    @NotBlank
    private String stateRegistration;

    @ApiModelProperty(example = "Company")
    @NotBlank
    private String name;

    @Min(value = 0, message = "Open hour must be between 0 and 23")
    @Max(value = 23, message = "Open hour must be between 0 and 23")
    @ApiModelProperty(example = "8")
    @NotNull
    private int openHour;

    @Min(value = 0, message = "Close hour must be between 0 and 23")
    @Max(value = 23, message = "Close hour must be between 0 and 23")
    @ApiModelProperty(example = "23")
    @NotNull
    private int closeHour;
}
