use `lampp-it`;

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    company_id BIGINT,
    payment_method ENUM('CREDIT_CARD', 'CASH', 'PIX'),
    delivery_method ENUM('DELIVERY', 'WITHDRAWAL'),
    status ENUM('REGISTERED', 'IN_PROGRESS', 'COMPLETED'),
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT fk_order_company FOREIGN KEY (company_id) REFERENCES company (id)
);