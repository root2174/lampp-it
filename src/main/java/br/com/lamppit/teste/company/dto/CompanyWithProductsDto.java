package br.com.lamppit.teste.company.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyWithProductsDto {
    Long id;
    String name;
    int closedHour;
    int openHour;
}
