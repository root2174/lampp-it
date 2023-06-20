package br.com.lamppit.teste.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Api(tags = "Auth controller")
public class AuthenticationUserController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    @ApiOperation(value = "Endpoint de login para utilização do restante dos endpoints", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequestDto dto)  {

        AuthenticationResponse response = service.authenticate(dto.getEmail(), dto.getPassword());

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", response.getToken());
        return ResponseEntity.ok().headers(header).body(response);
    }

    @PostMapping("/register")
    @ApiOperation(value = "Endpoint para registrar um novo usuário")
    @Transactional
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequestDto data,
            UriComponentsBuilder uriBuilder
    ) {

        var authenticationResponse = service.register(data.getEmail(), data.getPassword());

        var uri = uriBuilder.path("/user/{id}")
                .buildAndExpand(authenticationResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(authenticationResponse);

    }
}
