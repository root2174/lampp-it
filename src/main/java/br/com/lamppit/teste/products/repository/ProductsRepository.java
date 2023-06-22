package br.com.lamppit.teste.products.repository;

import br.com.lamppit.teste.products.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCompanyId(Long companyId, Pageable pagination);

    @Query("SELECT p FROM Product p WHERE p.id IN :productIds AND p.company.id = :companyId")
    List<Product> findAllByIdAndCompanyId(List<Long> productIds, Long companyId);
}
