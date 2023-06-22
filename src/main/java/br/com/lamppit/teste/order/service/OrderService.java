package br.com.lamppit.teste.order.service;

import br.com.lamppit.teste.company.model.Company;
import br.com.lamppit.teste.company.service.CompanyService;
import br.com.lamppit.teste.customer.service.CustomerService;
import br.com.lamppit.teste.order.dto.ChangeOrderRequestData;
import br.com.lamppit.teste.order.dto.CreateOrderRequestData;
import br.com.lamppit.teste.order.model.Order;
import br.com.lamppit.teste.order.model.OrderStatus;
import br.com.lamppit.teste.order.repository.OrderRepository;
import br.com.lamppit.teste.products.service.ProductsService;
import br.com.lamppit.teste.util.JwtUtilities;
import br.com.lamppit.teste.util.TimeUtils;
import br.com.lamppit.teste.validators.CanChangeOrderStatusValidator;
import br.com.lamppit.teste.validators.WithinBusinessHoursValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductsService productsService;
    private final CustomerService customerService;
    private final JwtUtilities jwtUtilities;
    private final OrderRepository orderRepository;
    private final CompanyService companyService;

    public Long create(HttpServletRequest request, CreateOrderRequestData data) {

        String email = jwtUtilities.extractUsername(
                jwtUtilities.getToken(request)
        );

        var customer = customerService
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found!"));

        var products = productsService.findAllByIdAndCompanyId(data.getProductsIds(), data.getCompanyId());

        if (products.size() != data.getProductsIds().size()) {
            throw new RuntimeException("No product found or product does not belong to company!");
        }

        Company company = products.get(0).getCompany();

        withinBusinessHoursValidation(company);


        var order = Order
                .builder()
                .orderStatus(OrderStatus.REGISTERED)
                .deliveryMethod(data.getDeliveryMethod())
                .paymentMethod(data.getPaymentMethod())
                .products(products)
                .company(company)
                .customer(customer)
                .build();

        orderRepository.save(order);

        return order.getId();

    }

    public void withinBusinessHoursValidation(Company company) {
        var now = TimeUtils.getCurrentHour();

        boolean isWithinBusinessHour = WithinBusinessHoursValidator
                .validate(now, company.getOpenHour(), company.getClosedHour());

        if (!isWithinBusinessHour) {
            throw new RuntimeException("Company is closed!");
        }
    }

    public void changeStatus(HttpServletRequest request, ChangeOrderRequestData data, Long orderId) {
        String email = jwtUtilities.extractUsername(
                jwtUtilities.getToken(request)
        );

        var company = companyService.findByEmail(email);

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found!"));

        if (!Objects.equals(order.getCompany().getId(), company.getId())) {
          throw new EntityNotFoundException("Order not found!");
        }

        var currentStatus = order.getOrderStatus();
        var newStatus = data.getOrderStatus();

        if (!CanChangeOrderStatusValidator.validate(currentStatus, newStatus)) {
            throw new RuntimeException("Transition invalid!");
        }

        order.setOrderStatus(data.getOrderStatus());
    }
}
