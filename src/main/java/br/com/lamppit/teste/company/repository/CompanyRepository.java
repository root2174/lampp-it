package br.com.lamppit.teste.company.repository;

import br.com.lamppit.teste.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
