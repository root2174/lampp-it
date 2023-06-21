package br.com.lamppit.teste.company.service;

import br.com.lamppit.teste.company.dto.RegisterCompanyRequestData;
import br.com.lamppit.teste.company.model.Company;
import br.com.lamppit.teste.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository repository;

    public Long create(RegisterCompanyRequestData data) {
        var company = Company
                .builder()
                .email(data.getEmail())
                .name(data.getName())
                .cnpj(data.getCnpj())
                .stateRegistration(data.getStateRegistration())
                .openHour(data.getOpenHour())
                .closeHour(data.getCloseHour())
                .build();

        repository.save(company);

        return company.getId();
    }
}
