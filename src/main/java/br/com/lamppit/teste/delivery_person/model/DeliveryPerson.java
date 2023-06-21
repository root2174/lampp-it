package br.com.lamppit.teste.delivery_person.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "delivery_person", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String phone;

}

