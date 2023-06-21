package br.com.lamppit.teste.company.model;

import br.com.lamppit.teste.products.model.Product;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "open_hour", nullable = false)
    private int openHour;
    @Column(name = "closed_hour", nullable = false)
    private int closedHour;

    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cnpj;

    @Column(name = "state_registration", nullable = false)
    private String stateRegistration;

    @OneToMany(mappedBy = "company")
    private List<Product> products;
}
