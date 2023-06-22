package br.com.lamppit.teste.validators;

import br.com.lamppit.teste.order.model.OrderStatus;

import java.util.Arrays;
import java.util.List;

public class CanChangeOrderStatusValidator {
    public static boolean validate(OrderStatus currentStatus, OrderStatus newStatus) {
        List<OrderStatus> validStatusOrder = Arrays.asList(
                OrderStatus.REGISTERED,
                OrderStatus.IN_PROGRESS,
                OrderStatus.COMPLETED,
                OrderStatus.DELIVERING,
                OrderStatus.DELIVERED
        );

        int currentStatusIndex = validStatusOrder.indexOf(currentStatus);
        int newStatusIndex = validStatusOrder.indexOf(newStatus);

        return newStatusIndex > currentStatusIndex;
    }
}
