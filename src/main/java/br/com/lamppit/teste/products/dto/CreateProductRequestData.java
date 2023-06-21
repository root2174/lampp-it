package br.com.lamppit.teste.products.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestData {

    @ApiModelProperty(example = "Baião de dois")
    @NotBlank
    private String name;

    @ApiModelProperty(example = "20.00")
    @NotNull
    private BigDecimal price;
}
