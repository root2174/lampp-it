package br.com.lamppit.teste.products.controller;

import br.com.lamppit.teste.products.dto.CreateProductRequestData;
import br.com.lamppit.teste.products.dto.CreateProductResponseData;
import br.com.lamppit.teste.products.service.ProductsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Api(tags = "Products controller")
public class ProductsController {

    private final ProductsService productsService;

    @PostMapping
    @Transactional
    public ResponseEntity<CreateProductResponseData> create(
            @RequestBody @Valid CreateProductRequestData data,
            HttpServletRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var product = productsService.create(request, data);

        var uri = uriBuilder.path("/api/v1/products/{id}").buildAndExpand(product.getId()).toUri();

        var response = CreateProductResponseData
                .builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();

        return ResponseEntity.created(uri).body(response);
    }
}
