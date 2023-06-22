package br.com.lamppit.teste.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsForClientDto {
    private Long id;
    private String name;
    private Long companyId;
}
