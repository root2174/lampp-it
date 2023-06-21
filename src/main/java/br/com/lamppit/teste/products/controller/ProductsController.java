package br.com.lamppit.teste.products.controller;

import br.com.lamppit.teste.products.dto.CreateProductRequestData;
import br.com.lamppit.teste.products.dto.CreateProductResponseData;
import br.com.lamppit.teste.products.dto.ProductsDto;
import br.com.lamppit.teste.products.model.Product;
import br.com.lamppit.teste.products.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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

    @GetMapping("/{company_id}")
    @ApiOperation(value = "Endpoint para listar produtos de uma determinada empresa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductsDto>> listByCompanyId(
            @PathVariable Long company_id,
            @PageableDefault(sort = {"name"}, size = 8) Pageable pageable
    ) {
        var products = productsService.listByCompanyId(company_id, pageable);

        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    @Transactional
    @ApiOperation(value = "Endpoint para criação de um produto", produces = MediaType.APPLICATION_JSON_VALUE)
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
