package br.com.lamppit.teste.delivery_person.controller;

import br.com.lamppit.teste.auth.service.AuthenticationService;
import br.com.lamppit.teste.delivery_person.dto.RegisterDeliveryPersonRequestData;
import br.com.lamppit.teste.delivery_person.dto.RegisterDeliveryPersonResponseData;
import br.com.lamppit.teste.delivery_person.service.DeliveryPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static br.com.lamppit.teste.auth.model.Role.CUSTOMER;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/delivery-person")
@Api(tags = "Delivery Person controller")
public class DeliveryPersonController {

    private final AuthenticationService authenticationService;
    private final DeliveryPersonService deliveryPersonService;

    @PostMapping
    @Transactional
    @ApiOperation(value = "Endpoint para cadastrar um(a) novo(a) entregador(a)")
    public ResponseEntity<RegisterDeliveryPersonResponseData> register(
            @RequestBody @Valid RegisterDeliveryPersonRequestData data,
            UriComponentsBuilder uriBuilder
    ) {

        var authResponse = authenticationService
                .register(data.getEmail(), data.getPassword(), CUSTOMER);

        var deliveryPersonId = deliveryPersonService.create(data);

        var uri = uriBuilder
                .path("/api/v1/deliver-person/{id}")
                .buildAndExpand(deliveryPersonId)
                .toUri();

        var res = RegisterDeliveryPersonResponseData
                .builder()
                .token(authResponse.getToken())
                .id(deliveryPersonId)
                .build();

        return ResponseEntity.created(uri).body(res);
    }
}
