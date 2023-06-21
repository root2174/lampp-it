package br.com.lamppit.teste.products.service;

import br.com.lamppit.teste.company.service.CompanyService;
import br.com.lamppit.teste.products.dto.CreateProductRequestData;
import br.com.lamppit.teste.products.model.Product;
import br.com.lamppit.teste.products.repository.ProductsRepository;
import br.com.lamppit.teste.util.JwtUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final JwtUtilities jwtUtilities;
    private final CompanyService companyService;
    private final ProductsRepository productsRepository;

    public Product create(
            HttpServletRequest request,
            CreateProductRequestData data
    ) {

        String email = jwtUtilities.extractUsername(
                jwtUtilities.getToken(request)
        );

        var company = companyService.findByEmail(email);

        var product = Product
               .builder()
               .price(data.getPrice())
               .name(data.getName())
               .company(company)
               .build();

      productsRepository.save(product);

      return product;
    }
}
