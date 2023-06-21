package br.com.lamppit.teste.customer.controller;

import br.com.lamppit.teste.auth.service.AuthenticationService;
import br.com.lamppit.teste.customer.dto.RegisterCustomerRequestData;
import br.com.lamppit.teste.customer.dto.RegisterCustomerResponseData;
import br.com.lamppit.teste.customer.service.CustomerService;
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

import static br.com.lamppit.teste.auth.model.Role.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
@Api(tags = "Customer controller")
public class CustomerController {

    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    @PostMapping
    @Transactional
    @ApiOperation(value = "Endpoint para cadastrar um novo cliente")
    public ResponseEntity<RegisterCustomerResponseData> register(
            @RequestBody @Valid RegisterCustomerRequestData data,
            UriComponentsBuilder uriBuilder
    ) {

        var authResponse = authenticationService
                .register(data.getEmail(), data.getPassword(), CUSTOMER);

        var customerId = customerService.create(data);

        var uri = uriBuilder
                .path("/api/v1/customer/{id}")
                .buildAndExpand(customerId)
                .toUri();

        var res = RegisterCustomerResponseData
                .builder()
                .token(authResponse.getToken())
                .id(customerId)
                .build();

        return ResponseEntity.created(uri).body(res);
    }
}
