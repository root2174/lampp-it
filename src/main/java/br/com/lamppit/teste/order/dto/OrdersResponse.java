package br.com.lamppit.teste.order.dto;

import br.com.lamppit.teste.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponse {
    private OrderStatus orderStatus;
    private List<ProductsForClientDto> products;
}
