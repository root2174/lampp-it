package br.com.lamppit.teste.order.repository;

import br.com.lamppit.teste.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByCompanyId(Long orderId);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :id AND o.orderStatus != 'DELIVERED'")
    List<Order> findAllByCustomerId(Long id);

    @Query("SELECT o FROM Order o WHERE o.orderStatus = 'COMPLETED'")
    List<Order> findAllAvailableForDelivery();
}
