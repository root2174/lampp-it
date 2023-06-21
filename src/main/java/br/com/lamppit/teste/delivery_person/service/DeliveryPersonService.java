package br.com.lamppit.teste.delivery_person.service;

import br.com.lamppit.teste.delivery_person.dto.RegisterDeliveryPersonRequestData;
import br.com.lamppit.teste.delivery_person.model.DeliveryPerson;
import br.com.lamppit.teste.delivery_person.repository.DeliveryPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryPersonService {

    private final DeliveryPersonRepository repository;

    public Long create(RegisterDeliveryPersonRequestData data) {
        var deliveryPerson = DeliveryPerson
                .builder()
                .email(data.getEmail())
                .name(data.getName())
                .phone(data.getPhone())
                .build();

        repository.save(deliveryPerson);

        return deliveryPerson.getId();
    }
}
