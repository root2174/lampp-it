package br.com.lamppit.teste.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "company", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
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
    private int closeHour;

    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cnpj;

    @Column(name = "state_registration", nullable = false)
    private String stateRegistration;
}
