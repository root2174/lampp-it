package br.com.lamppit.teste.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponseData {
    private Long id;
    private String name;
    private BigDecimal price;
}
