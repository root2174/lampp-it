package br.com.lamppit.teste.products.repository;

import br.com.lamppit.teste.products.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCompanyId(Long companyId, Pageable pagination);
}
