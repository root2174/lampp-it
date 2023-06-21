package br.com.lamppit.teste.customer.service;

import br.com.lamppit.teste.customer.dto.RegisterCustomerRequestData;
import br.com.lamppit.teste.customer.model.Customer;
import br.com.lamppit.teste.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;

    public Long create(RegisterCustomerRequestData data) {
        var customer = Customer
                .builder()
                .email(data.getEmail())
                .name(data.getName())
                .phone(data.getPhone())
                .build();

        repository.save(customer);

        return customer.getId();
    }
}
