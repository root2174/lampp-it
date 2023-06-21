package br.com.lamppit.teste.delivery_person.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDeliveryPersonResponseData {
    private String token;
    private Long id;
}
