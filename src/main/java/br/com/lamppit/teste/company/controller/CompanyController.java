package br.com.lamppit.teste.company.controller;


import br.com.lamppit.teste.auth.service.AuthenticationService;
import br.com.lamppit.teste.company.dto.RegisterCompanyRequestData;
import br.com.lamppit.teste.company.dto.RegisterCompanyResponseData;
import br.com.lamppit.teste.company.service.CompanyService;
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

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
@Api(tags = "Company controller")
public class CompanyController {

    private final AuthenticationService authenticationService;
    private final CompanyService companyService;

    @PostMapping
    @Transactional
    @ApiOperation(value = "Endpoint para registrar uma nova empresa")
    public ResponseEntity<RegisterCompanyResponseData> register(
            @RequestBody @Valid RegisterCompanyRequestData data,
            UriComponentsBuilder uriBuilder
    ) {
        var authenticationResponse = authenticationService
                .register(data.getEmail(), data.getPassword(), COMPANY);

        var companyId = companyService.create(data);

        var uri = uriBuilder.path("/company/{id}")
                .buildAndExpand(companyId).toUri();

        var response = RegisterCompanyResponseData
                .builder()
                .id(companyId)
                .token(authenticationResponse.getToken())
                .build();

        return ResponseEntity.created(uri).body(response);
    }
}
