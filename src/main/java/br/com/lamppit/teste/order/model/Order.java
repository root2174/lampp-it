package br.com.lamppit.teste.order.model;

import br.com.lamppit.teste.company.model.Company;
import br.com.lamppit.teste.customer.model.Customer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method", nullable = false)
    @Enumerated
    private PaymentMethod paymentMethod;

    @Enumerated
    @Column(name = "delivery_method", nullable = false)
    private DeliveryMethod deliveryMethod;

    @Enumerated
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
