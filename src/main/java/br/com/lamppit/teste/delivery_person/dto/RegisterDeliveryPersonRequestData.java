package br.com.lamppit.teste.delivery_person.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDeliveryPersonRequestData {

    @Email
    @NotBlank
    @ApiModelProperty(example = "delivery@delivery.com")
    private String email;

    @NotBlank
    @ApiModelProperty(example = "delivery")
    private String name;

    @NotBlank
    @ApiModelProperty(example = "8599999999")
    private String phone;

    @NotBlank
    @ApiModelProperty(example = "123456")
    private String password;
}
