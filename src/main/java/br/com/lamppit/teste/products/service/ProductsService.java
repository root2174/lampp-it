package br.com.lamppit.teste.products.service;

import br.com.lamppit.teste.company.service.CompanyService;
import br.com.lamppit.teste.products.dto.CreateProductRequestData;
import br.com.lamppit.teste.products.dto.ProductsDto;
import br.com.lamppit.teste.products.model.Product;
import br.com.lamppit.teste.products.repository.ProductsRepository;
import br.com.lamppit.teste.util.JwtUtilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final JwtUtilities jwtUtilities;
    private final CompanyService companyService;
    private final ProductsRepository productsRepository;
    private final ModelMapper modelMapper;

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

    public Page<ProductsDto> listByCompanyId(Long companyId, Pageable pagination) {
        return productsRepository.findAllByCompanyId(companyId, pagination)
                .map(product -> modelMapper.map(product, ProductsDto.class));
    }
}
