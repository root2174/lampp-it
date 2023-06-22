package br.com.lamppit.teste.order.repository;

import br.com.lamppit.teste.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByCompanyId(Long orderId);
}
