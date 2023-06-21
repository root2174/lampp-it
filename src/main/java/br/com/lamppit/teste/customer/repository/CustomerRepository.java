package br.com.lamppit.teste.customer.repository;

import br.com.lamppit.teste.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
