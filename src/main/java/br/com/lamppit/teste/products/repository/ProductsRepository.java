package br.com.lamppit.teste.products.repository;

import br.com.lamppit.teste.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
