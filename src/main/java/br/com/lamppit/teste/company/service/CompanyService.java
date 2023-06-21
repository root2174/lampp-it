package br.com.lamppit.teste.company.service;

import br.com.lamppit.teste.company.dto.CompanyWithProductsDto;
import br.com.lamppit.teste.company.dto.RegisterCompanyRequestData;
import br.com.lamppit.teste.company.model.Company;
import br.com.lamppit.teste.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository repository;
    private final ModelMapper modelMapper;

    public Long create(RegisterCompanyRequestData data) {
        var company = Company
                .builder()
                .email(data.getEmail())
                .name(data.getName())
                .cnpj(data.getCnpj())
                .stateRegistration(data.getStateRegistration())
                .openHour(data.getOpenHour())
                .closedHour(data.getCloseHour())
                .build();

        repository.save(company);

        return company.getId();
    }

    public Company findByEmail(String email) {
        return repository
                .findCompanyByEmail(email)
                .orElseThrow(() -> new RuntimeException("Company not found."));
    }

    public Page<CompanyWithProductsDto> findAllWithProducts(
            Pageable pagination
    ) {
        var companiesWithProducts = repository.findCompaniesWithProducts(pagination);
        return companiesWithProducts.map(company -> modelMapper.map(company, CompanyWithProductsDto.class));
    }
}
