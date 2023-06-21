package br.com.lamppit.teste.company.repository;

import br.com.lamppit.teste.company.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findCompanyByEmail(String email);

    @Query(
            value = "SELECT * FROM company c WHERE EXISTS (SELECT 1 FROM products p WHERE p.company_id = c.id)",
            countQuery = "SELECT count(*) FROM company c WHERE EXISTS (SELECT 1 FROM products p WHERE p.company_id = c.id)",
            nativeQuery = true
    )
    Page<Company> findCompaniesWithProducts(Pageable pagination);
}
