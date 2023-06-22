package br.com.lamppit.teste.order.controller;

import br.com.lamppit.teste.order.dto.ChangeOrderRequestData;
import br.com.lamppit.teste.order.dto.CreateOrderRequestData;
import br.com.lamppit.teste.order.dto.CreateOrderResponseData;
import br.com.lamppit.teste.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
@Api(tags = "Orders controller")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Transactional
    @ApiOperation(value = "Endpoint para realizar um novo pedido")
    public ResponseEntity<CreateOrderResponseData> create(
            @RequestBody @Valid CreateOrderRequestData data,
            UriComponentsBuilder uriBuilder,
            HttpServletRequest request
    ) {
        var id = orderService.create(request, data);

        var uri = uriBuilder
                .path("/api/v1/orders/{id}")
                .buildAndExpand(id)
                .toUri();

        var response = CreateOrderResponseData
                .builder()
                .id(id)
                .build();

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/change-status/{orderId}")
    @Transactional
    @ApiOperation(value = "Endpoint para realizar um novo pedido")
    public ResponseEntity changeStatus(
            @RequestBody @Valid ChangeOrderRequestData data,
            HttpServletRequest request,
            @PathVariable Long orderId) {

        orderService.changeStatus(request, data, orderId);

        return ResponseEntity.ok().build();
    }
}
